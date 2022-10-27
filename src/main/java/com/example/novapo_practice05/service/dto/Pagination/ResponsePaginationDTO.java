package com.example.novapo_practice05.service.dto.Pagination;

import java.util.List;

import lombok.Getter;

@Getter
public class ResponsePaginationDTO<T> {

    private List<T> data;
    private int page;
    private int limit;
    private int totalPage;

    private int count;

    public ResponsePaginationDTO<T> setPage(int page) {
        this.page = page;
        return this;
    }

    public ResponsePaginationDTO<T> setTotalPage(int page) {
        this.totalPage = page;
        return this;
    }

    public ResponsePaginationDTO<T> setLimt(int limit) {
        this.limit = limit;
        return this;
    }

    public ResponsePaginationDTO<T> setData(List<T> data) {
        this.data = data;
        return this;
    }

    public ResponsePaginationDTO<T> setCount(int count) {
        this.count = count;
        return this;
    }


}
