package com.qing.rbac.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "rbac_permission")
@Data
public class Permission {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 权限名称
     */
    @Column(nullable = false, length = 36, unique = true)
    private String name;

    /**
     * 权限标识
     */
    @Column(nullable = false, length = 36, unique = true)
    private String permissionKey;

    /**
     * 权限类型
     */
    @Enumerated(EnumType.STRING)
    private Type type;

    /**
     * 菜单路径
     */
    private String path;

    /**
     * 资源
     */
    private String resource;

    /**
     * 权限描述
     */
    private String description;

    /**
     * 是否可用
     */
    private Boolean enable = false;

    /**
     * 权重，即排序位置
     */
    private Integer weight;

    /**
     * 用于返回combobox下拉框字段
     * @return
     */
    @JsonProperty(value = "text")
    public String getText() {
        return this.name;
    }

    /**
     * 父节点
     */
    @JsonIgnore
    @ManyToOne
    private Permission parent;

    /**
     * 子节点
     */
    @OneToMany(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "parent_id", updatable = false)
    private List<Permission> children;

    /**
     * 权限类型枚举
     */
    public enum Type {
        MENU("菜单"),
        FUNCTION("功能"),
        BLOCK("区域");

        private String display;

        Type(String display) {
            this.display = display;
        }

        public String display() {
            return display;
        }

        @Override
        public String toString() {
            return this.display + "[" + this.name() + "]";
        }
    }
}
