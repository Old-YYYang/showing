package com.qing.rbac.controller.system;

import com.qing.rbac.dao.PermissionDao;
import com.qing.rbac.entity.Permission;
import com.qing.rbac.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/system/permission")
@Slf4j
@Transactional
public class PermissionController {

    @Autowired
    private PermissionDao permissionDao;

    @GetMapping
    public void index() {}

    @GetMapping(value = {"form", "load"})
    public String form(Long id, Model model) {
        if (null != id) {
            model.addAttribute("permission", permissionDao.findOne(id));
        }
        return "/system/permission/form";
    }

    @PostMapping("list")
    @ResponseBody
    public List<Permission> list() {
        List<Permission> roots = permissionDao.findAllByParentIsNull();
        revers(roots);
        return roots;
    }

    @PostMapping("combo")
    @ResponseBody
    public List<Permission> combo() {
        List<Permission> roots = permissionDao.findAllByParentIsNull();
        revers(roots);
        return roots;
    }

    @PostMapping(value = {"save", "update"})
    @ResponseBody
    @Transactional
    public Result save(@Valid Permission permission, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            log.info("permission.name is {}", permission.getName());
            permissionDao.save(permission);
            return Result.success();
        } else {
            return Result.error("校验不通过");
        }
    }

    @GetMapping("delete")
    @ResponseBody
    @Transactional
    public Result delete(Long id) {
        Permission permission = permissionDao.findOne(id);
        if (null != permission) {
            permissionDao.delete(permission);
            return Result.success();
        }
        return Result.error("数据不存在");
    }

    /**
     * 递归子节点
     * @param roots 父节点
     */
    private void revers(List<Permission> roots) {
        for (Permission permission: roots) {
            List<Permission> children = permissionDao.findAllByParent(permission);
            revers(children);
            permission.setChildren(children);
        }
    }
}
