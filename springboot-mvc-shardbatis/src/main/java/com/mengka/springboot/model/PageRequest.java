package com.mengka.springboot.model;

/**
 * @author huangyy
 * @description
 * @date 2017/04/30.
 */
public class PageRequest {
    private Integer page;
    private Integer limit;
    private String sort;
    private PageRequest.Order order;

    public PageRequest() {
    }

    public Integer getPage() {
        return this.page;
    }

    public Integer getLimit() {
        return this.limit;
    }

    public String getSort() {
        return this.sort;
    }

    public PageRequest.Order getOrder() {
        return this.order;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public void setLimit(Integer limit) {
        this.limit = limit;
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public void setOrder(PageRequest.Order order) {
        this.order = order;
    }

    public boolean equals(Object o) {
        if(o == this) {
            return true;
        } else if(!(o instanceof PageRequest)) {
            return false;
        } else {
            PageRequest other = (PageRequest)o;
            if(!other.canEqual(this)) {
                return false;
            } else {
                label59: {
                    Integer this$page = this.getPage();
                    Integer other$page = other.getPage();
                    if(this$page == null) {
                        if(other$page == null) {
                            break label59;
                        }
                    } else if(this$page.equals(other$page)) {
                        break label59;
                    }

                    return false;
                }

                Integer this$limit = this.getLimit();
                Integer other$limit = other.getLimit();
                if(this$limit == null) {
                    if(other$limit != null) {
                        return false;
                    }
                } else if(!this$limit.equals(other$limit)) {
                    return false;
                }

                String this$sort = this.getSort();
                String other$sort = other.getSort();
                if(this$sort == null) {
                    if(other$sort != null) {
                        return false;
                    }
                } else if(!this$sort.equals(other$sort)) {
                    return false;
                }

                PageRequest.Order this$order = this.getOrder();
                PageRequest.Order other$order = other.getOrder();
                if(this$order == null) {
                    if(other$order != null) {
                        return false;
                    }
                } else if(!this$order.equals(other$order)) {
                    return false;
                }

                return true;
            }
        }
    }

    protected boolean canEqual(Object other) {
        return other instanceof PageRequest;
    }

    public int hashCode() {
        boolean PRIME = true;
        byte result = 1;
        Integer $page = this.getPage();
        int result1 = result * 59 + ($page == null?43:$page.hashCode());
        Integer $limit = this.getLimit();
        result1 = result1 * 59 + ($limit == null?43:$limit.hashCode());
        String $sort = this.getSort();
        result1 = result1 * 59 + ($sort == null?43:$sort.hashCode());
        PageRequest.Order $order = this.getOrder();
        result1 = result1 * 59 + ($order == null?43:$order.hashCode());
        return result1;
    }

    public String toString() {
        return "PageRequest(page=" + this.getPage() + ", limit=" + this.getLimit() + ", sort=" + this.getSort() + ", order=" + this.getOrder() + ")";
    }

    static enum Order {
        ASC,
        DESC;

        private Order() {
        }
    }
}