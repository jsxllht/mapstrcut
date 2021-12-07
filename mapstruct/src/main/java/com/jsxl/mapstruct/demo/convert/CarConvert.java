package com.jsxl.mapstruct.demo.convert;

import com.jsxl.mapstruct.demo.dto.CarDTO;
import com.jsxl.mapstruct.demo.dto.DriverDTO;
import com.jsxl.mapstruct.demo.dto.PartDTO;
import com.jsxl.mapstruct.demo.vo.CarVO;
import com.jsxl.mapstruct.demo.vo.DriverVO;
import com.jsxl.mapstruct.demo.vo.VehicleVO;
import org.mapstruct.AfterMapping;
import org.mapstruct.BeanMapping;
import org.mapstruct.InheritConfiguration;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Mappings;
import org.mapstruct.factory.Mappers;

import java.util.List;

/**
 * 使用mapstruct 的步骤:
 * 1.引入依赖
 * 2.新建一个抽 象类或者接口并标注@Mapper
 * 3.写一个方法
 * 4.获取对象并使用
 * 5.相关的pojo之间的转化
 */
@Mapper(componentModel = "spring")
public abstract class CarConvert {
    public static CarConvert INSTANCE = Mappers.getMapper(CarConvert.class);

    /**
     * CarDto-- >CarVo
     */
    @Mappings(value = {
            @Mapping(source = "totalPrice", target = "totalPrice", numberFormat = "#.OO"),
            @Mapping(source = "publishDate", target = "publishDate", numberFormat = "yyyy-MM-dd HH:mm:ss"),
            @Mapping(target = "color", ignore = true),
            @Mapping(source = "brand", target = "brandName"),
            @Mapping(source = "driverDTO", target = "driverVO")
    })
    public abstract CarVO Dto2Vo(CarDTO carDTO);

    @Mapping(source = "id", target = "driverId")
    @Mapping(source = "name", target = "fullName")
    public abstract DriverVO driverDTO2DriverVO(DriverDTO driverDTO);

    @AfterMapping //表示mapstruct 在调用完自动转换的方法之后，会来自动调用本方法
    public void dto2voAfter(CarDTO carDTO, @MappingTarget CarVO carVO) {
// @MappingTarget :表示传来的carVO对象是已经赋值过的
        List<PartDTO> partDTOS = carDTO.getPartDTOS();
        boolean hasPart = partDTOS != null && !partDTOS.isEmpty();
        carVO.setHasPart(hasPart);
    }

    /**
     * dto2vo这个方法的批量转换
     */
    public abstract List<CarVO> dtos2vos(List<CarDTO> carDTO);


//    @Mapping(source = "price" ,target = "price",ignore = true)
    @BeanMapping(ignoreByDefault = true)
    @Mapping(source = "id", target = "id")
//    @Mapping(source = "price" , target = "price" , numberFormat = "#.00")
    @Mapping(source = "brand", target = "brandName")
    public abstract VehicleVO carDTO2vehicleVO(CarDTO carDTO);


//    @BeanMapping(ignoreByDefault = true)
//    @Mapping(source = "id",target = "id")
//    @Mapping(source = "brand" , target = "brandName")
    @InheritConfiguration
    @Mapping(target = "id", ignore = true)
    public abstract void updateVehicleVO(CarDTO carDTO, @MappingTarget VehicleVO vehicleVO);


    /**
     * 测试@InheritInverseConfigurat ion反向继承
     * name:指定使用哪一个方法的配置，
     * @param vehicleVO
     * @return
     */
    @BeanMapping(ignoreByDefault = true)
//    @Mapping(source = "id" ,target = "id")
//    @Mapping(source = "brandName" ,target = "brand")
    @InheritInverseConfiguration(name = "carDTO2vehicleVO")
    @Mapping(target = "id", ignore = true)
    public abstract CarDTO vehicleVO2CarDTO(VehicleVO vehicleVO);
}