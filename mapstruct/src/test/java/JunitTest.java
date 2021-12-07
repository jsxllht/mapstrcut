import com.jsxl.entity.DTO.UserRoleDto;
import com.jsxl.entity.Role;
import com.jsxl.entity.User;
import com.jsxl.mapper.UserRoleMapper;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;


public class JunitTest {
    User user = null;

//    @Autowired
//    UserRoleMapper userRoleMapper;
    /*

    模拟从数据库中查出user对象
    */
    @Before
    public void before() {
        Role role = new Role(2L, "administrator", "超级管理员");
        user = new User(1L, "zhangsan", "12345", "17677778888", "123@qq.com", role);
    }

    @Test
    public void test1() throws InterruptedException {
        UserRoleDto userRoleDto = new UserRoleDto();
        userRoleDto.setUserId(user.getId());
        userRoleDto.setName(user.getUsername());
        userRoleDto.setRoleName(user.getRole().getRoleName());
        //then
//        assertThat( userRoleDto ).isNotNull();
//        assertThat( userRoleDto.getName()).isEqualTo( "zhangsan" );
//        assertThat( userRoleDto.getRoleName() ).isEqualTo( "administrator" );
//        assertThat( userRoleDto.getUserId() ).isEqualTo( 1L);
        System.out.println(userRoleDto);

    }

    @Test
    public void testMapStruct() throws InterruptedException {
        UserRoleDto userRoleDto = UserRoleMapper.INSTANCES.toUserRoleDto(user);
        System.out.println(userRoleDto);
    }

    @Test
    public void testMapStructUpdate() throws InterruptedException {
        UserRoleDto userRoleDto = new UserRoleDto();
        UserRoleMapper.INSTANCES.update(user, userRoleDto);
        System.out.println(userRoleDto);

    }
}



//@InheritInverseConfiguration使用该注解的前提是有了正映射,就可以使用它达到逆映射
//@InheritConfiguration可以继承@Mapping,@BeanMapping,@IterableMapping的映射规则。
//@InheritConfiguration注解的方法上,有需要映射的字段,它会搜索有相同配置的映射,找到了直接复用此映射；若找到多个方法上都有满足此映射的配置,需要制定@InheritConfiguration#name的值,制定继承方法的映射。
//        使用Spring依赖注入
//
//// 这里主要是这个***componentModel 属性,它的值就是当前要使用的依赖注入的环境***
//@Mapper(componentModel = “spring”)
//public interface CustomerMapper {undefined
//
//    @Mapping(source = "name", target = "customerName")
//    CustomerDto toCustomerDto(Customer customer);
//}