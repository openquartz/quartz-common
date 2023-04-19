package com.openquartz.common.util.page;

/**
 * 汇总信息
 *
 * @author svnee
 */
public class PageTotal {

    /**
     * 总行数
     */
    private Integer total;

    /**
     * 分片页大小
     */
    private Integer pageSize;

    public static PageTotal of(Integer total, Integer pageSize) {
        PageTotal pageTotal = new PageTotal();
        pageTotal.setPageSize(pageSize);
        pageTotal.setTotal(total);
        return pageTotal;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }
}
