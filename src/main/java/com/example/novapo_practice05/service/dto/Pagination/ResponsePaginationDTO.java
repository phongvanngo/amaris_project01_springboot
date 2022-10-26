package com.example.novapo_practice05.service.dto.Pagination;

import java.util.List;

import lombok.Getter;

@Getter
public class ResponsePaginationDTO<T> {

    private List<T> data;
    private Integer page;
    private Integer limit;
    private Integer totalPage;

    public ResponsePaginationDTO<T> setPage(Integer page) {
        this.page = page;
        return this;
    }

    public ResponsePaginationDTO<T> setTotalPage(Integer page) {
        this.totalPage = page;
        return this;
    }

    public ResponsePaginationDTO<T> setLimt(Integer limit) {
        this.limit = limit;
        return this;
    }

    public ResponsePaginationDTO<T> setData(List<T> data) {
        this.data = data;
        return this;
    }
}
