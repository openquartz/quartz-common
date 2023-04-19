package com.openquartz.common.spi;

import java.util.Objects;

/**
 * The type ExtensionEntity.
 *
 * @author svnee
 */
public final class ExtensionEntity {
    
    private final String name;
    
    private final Class<?> serviceClass;
    
    private final Integer order;
    
    private final ScopeType scopeType;

    public ExtensionEntity(String name, Class<?> serviceClass, Integer order, ScopeType scopeType) {
        this.name = name;
        this.serviceClass = serviceClass;
        this.order = order;
        this.scopeType = scopeType;
    }

    public String getName() {
        return name;
    }

    public Class<?> getServiceClass() {
        return serviceClass;
    }

    public Integer getOrder() {
        return order;
    }

    public ScopeType getScopeType() {
        return scopeType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        ExtensionEntity that = (ExtensionEntity) o;
        return Objects.equals(name, that.name) && Objects.equals(serviceClass, that.serviceClass)
            && Objects.equals(order, that.order) && scopeType == that.scopeType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, serviceClass, order, scopeType);
    }

    @Override
    public String toString() {
        return "ExtensionEntity{" +
            "name='" + name + '\'' +
            ", serviceClass=" + serviceClass +
            ", order=" + order +
            ", scopeType=" + scopeType +
            '}';
    }
}
