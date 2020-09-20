package com.xunfang.pojo;

public class Pager {
//    带显示页 当前是第几页
    private int curPage;
//    每页显示多少行数据
    private int perPageRows;
//    数据总数
    private int rowCount;
//    总页数
    private int pageCount;

    public int getCurPage() {
        return curPage;
    }

    public void setCurPage(int curPage) {
        this.curPage = curPage;
    }

    public int getPerPageRows() {
            return perPageRows;
    }

    public void setPerPageRows(int perPageRows) {
        this.perPageRows = perPageRows;
    }

    public int getRowCount() {
        return rowCount;
    }

    public void setRowCount(int rowCount) {
        this.rowCount = rowCount;
    }

//    根据 总数和每页显示的数 得出 总页数
    public int getPageCount() {
        return (rowCount+perPageRows-1)/perPageRows;
    }

    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

//    分页显示时，获取当前页数的 第一个数据的 索引（下标）
//     (当前页数-1)*每页显示行数
    public int getFirstLimitParam(){
        return (this.curPage-1)*this.perPageRows;
    }
}
