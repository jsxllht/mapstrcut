package com.jsxl.mapper;

import com.jsxl.entity.DTO.UserRoleDto;
import com.jsxl.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;
import org.mapstruct.factory.Mappers;


@Mapper(unmappedTargetPolicy = ReportingPolicy.IGNORE)
//定义这是一个MapStruct对象属性转换接口，在这个类里面规定转换规则
public interface UserRoleMapper {
    //在项目构建时，会自动生成改接口的实现类，这个实现类将实现对象属性值复制
    /**
     * **获取该类自动生成的实现类的实例
     * 接口中的属性都是 public static final 的 方法都是public abstract的**
     */
    UserRoleMapper INSTANCES = Mappers.getMapper(UserRoleMapper.class);
    /**
     * 这个方法就是用于实现对象属性复制的方法
     *
     * @param user 这个参数就是源对象，也就是需要被复制的对象
     * @return 返回的是目标对象，就是最终的结果对象
     * @Mapping 用来定义属性复制规则 source 指定源对象属性 target指定目标对象属性
     */
    @Mappings({
            @Mapping(source = "id", target = "userId"),
            @Mapping(source = "username", target = "name"),
            @Mapping(source = "role.roleName", target = "roleName")
    })
    UserRoleDto toUserRoleDto(User user);

    /**
     * 更新对象属性
     * @param user
     * @param userRoleDto
     * @return
     */
    @Mappings({
            @Mapping(source = "id", target = "userId"),
            @Mapping(source = "username", target = "name"),
            @Mapping(source = "role.roleName", target = "roleName")
    })
    void update(User user, @MappingTarget UserRoleDto userRoleDto);
}