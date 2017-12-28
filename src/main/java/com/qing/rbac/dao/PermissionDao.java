package com.qing.rbac.dao;

import com.qing.rbac.entity.Permission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PermissionDao extends JpaRepository<Permission, Long> {

    /**
     * 查找所有父类
     * @return 父类集合
     */
    List<Permission> findAllByParentIsNull();

    /**
     * 根据父节点获取子节点
     * @param parent 父节点
     * @return 子节点
     */
    List<Permission> findAllByParent(Permission parent);
}
