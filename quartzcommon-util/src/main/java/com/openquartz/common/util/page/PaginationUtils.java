package com.openquartz.common.util.page;

import java.util.Collections;
import java.util.List;

/**
 * @author svnee
 **/
public final class PaginationUtils {

    private PaginationUtils() {
    }

    /**
     * 空数据集
     *
     * @param pageNo 当前查询页码
     * @param pageSize 页面大小
     * @param <T> 数据
     * @return 分页结果
     */
    public static <T> Pagination<T> empty(Integer pageNo, Integer pageSize) {
        Pagination<T> pagination = new Pagination<>();
        pagination.setTotalRecords(0L);
        pagination.setPageSize(Long.valueOf(pageSize));
        pagination.setPage(Long.valueOf(pageNo));
        pagination.setModelList(Collections.emptyList());
        return pagination;
    }

    /**
     * copy
     */
    public static <T> Pagination<T> copy(Pagination<?> source, List<T> modelList) {
        Pagination<T> pagination = new Pagination<>();
        pagination.setPage(source.getPage());
        pagination.setTotalRecords(source.getTotalRecords());
        pagination.setModelList(modelList);
        pagination.setPageSize(source.getPageSize());
        pagination.setInformation(source.getInformation());
        pagination.setExtendsSize(source.getExtendsSize());
        return pagination;
    }

}