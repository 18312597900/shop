package com.xunfang.pojo;

import java.util.List;

public class TreeNode {
//    节点id
    private int id;
//    节点名称
    private String text;
//    父id
    private int fid;
//    子节点
    private List<TreeNode> children;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getFid() {
        return fid;
    }

    public void setFid(int fid) {
        this.fid = fid;
    }

    public List<TreeNode> getChildren() {
        return children;
    }

    public void setChildren(List<TreeNode> children) {
        this.children = children;
    }

    @Override
    public String toString() {
        return "TreeNode{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", fid=" + fid +
                ", children=" + children +
                '}';
    }
}
