package com.example.generic;

import com.github.pagehelper.Page;

import java.util.Collection;

/**
 * @author 朱伟伟
 * @date 2020-12-27 12:02:17
 * @description
 */
public class ArrayData<T> extends BaseEntity {
    private static final long serialVersionUID = 403443358176094884L;
    /**
     * 数据
     */
    private Collection<T> data;
    /**
     * 当前页
     */
    private long pageNum;
    /**
     * 每页的数量
     */
    private long pageSize;
    /**
     * 总页数
     */
    private long pages;
    /**
     * 是否有下一页
     */
    private boolean hasNextPage = false;
    /**
     * 总数
     */
    private long total;


    public ArrayData(Page<T> page) {
        this.data = page.getResult();
        this.pageNum = page.getPageNum();
        this.pageSize = page.getPageSize();
        this.pages = page.getPages();
        this.hasNextPage = page.getPageNum() < page.getPages();
        this.total = page.getTotal();
    }

    public ArrayData(com.baomidou.mybatisplus.extension.plugins.pagination.Page<T> page) {
        this.data = page.getRecords();
        this.pageNum = page.getCurrent();
        this.pageSize = page.getSize();
        this.pages = page.getPages();
        this.hasNextPage = page.hasNext();
        this.total = page.getTotal();
    }

    public ArrayData(Collection<T> data) {
        this.data = data;
        this.pageNum = 1;
        this.pageSize = data.size();
        this.pages = data.size();
        this.total = data.size();
    }

    public Collection<T> getData() {
        return data;
    }

    public void setData(Collection<T> data) {
        this.data = data;
    }

    public long getPageNum() {
        return pageNum;
    }

    public void setPageNum(long pageNum) {
        this.pageNum = pageNum;
    }

    public long getPageSize() {
        return pageSize;
    }

    public void setPageSize(long pageSize) {
        this.pageSize = pageSize;
    }

    public long getPages() {
        return pages;
    }

    public void setPages(long pages) {
        this.pages = pages;
    }

    public boolean isHasNextPage() {
        return hasNextPage;
    }

    public void setHasNextPage(boolean hasNextPage) {
        this.hasNextPage = hasNextPage;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
