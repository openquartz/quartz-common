package com.openquartz.common.util.json;

import static com.openquartz.easyfile.common.util.ExceptionUtils.rethrow;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import java.io.IOException;
import java.lang.reflect.Type;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * JsonUtils
 *
 * @author svnee
 **/
public abstract class JacksonHandler implements JsonFacade {

    private final ObjectMapper mapper = newMapper();

    /**
     * 基于默认配置, 创建一个新{@link ObjectMapper},
     * 随后可以定制化这个新{@link ObjectMapper}.
     */
    public abstract ObjectMapper newMapper();

    /**
     * 获取默认{@link ObjectMapper}.
     * 直接使用默认{@link ObjectMapper}时需要小心,
     * 因为{@link ObjectMapper}类是可变的,
     * 对默认 ObjectMapper 的改动会影响所有默认ObjectMapper的依赖方.
     * 如果需要在当前上下文定制化{@link ObjectMapper},
     * 建议使用{@link #newMapper()}方法创建一个新的{@link ObjectMapper}.
     *
     * @see #newMapper()
     */
    public ObjectMapper mapper() {
        return mapper;
    }

    @Override
    public String toJson(Object obj) {
        try {
            return mapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            return rethrow(e);
        }
    }

    @Override
    public <T> T parseObject(String text, Class<T> clazz) {
        try {
            return mapper.readValue(text, clazz);
        } catch (IOException e) {
            return rethrow(e);
        }
    }

    @Override
    public <T> T parseObject(byte[] json, Class<T> type) {
        try {
            return mapper.readValue(json, type);
        } catch (IOException e) {
            return rethrow(e);
        }
    }

    @Override
    public <T> T parseObject(String text, TypeReference<T> typeReference) {
        try {
            Type type = typeReference.getType();
            JavaType javaType = mapper.getTypeFactory().constructType(type);
            return mapper.readValue(text, javaType);
        } catch (IOException e) {
            return rethrow(e);
        }
    }

    @Override
    public <T> T parseObject(byte[] json, TypeReference<T> typeReference) {
        try {
            Type type = typeReference.getType();
            JavaType javaType = mapper.getTypeFactory().constructType(type);
            return mapper.readValue(json, javaType);
        } catch (IOException e) {
            return rethrow(e);
        }
    }

    @Override
    public <T> List<T> parseArray(String text, Class<T> clazz) {
        return parseCollection(text, List.class, clazz);
    }

    @Override
    public <T> Set<T> parseSet(String json, Class<T> type) {
        return parseCollection(json, Set.class, type);
    }

    @Override
    public byte[] toJsonAsBytes(Object obj) {
        try {
            return mapper.writeValueAsBytes(obj);
        } catch (JsonProcessingException e) {
            return rethrow(e);
        }
    }

    private <V, C extends Collection<?>, T> V parseCollection(String json, Class<C> collectionType,
        Class<T> elementType) {
        try {
            TypeFactory typeFactory = mapper.getTypeFactory();
            CollectionType javaType = typeFactory.constructCollectionType(collectionType, elementType);
            return mapper.readValue(json, javaType);
        } catch (IOException e) {
            return rethrow(e);
        }
    }
}
