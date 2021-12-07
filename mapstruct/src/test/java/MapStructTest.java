import com.jsxl.mapstruct.demo.convert.CarConvert;
import com.jsxl.mapstruct.demo.dto.CarDTO;
import com.jsxl.mapstruct.demo.dto.DriverDTO;
import com.jsxl.mapstruct.demo.dto.PartDTO;
import com.jsxl.mapstruct.demo.vo.CarVO;
import com.jsxl.mapstruct.demo.vo.DriverVO;
import com.jsxl.mapstruct.demo.vo.VehicleVO;
import org.junit.Test;

import javax.annotation.Resource;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class MapStructTest {
    /*
    测试传统的通过getter.和Isetter赋值完成pojo之间转换
    *
    CarDto-->CarVo
    */
    @Test
    public void test1() {
        //模拟业务构造出的CarDTO对象
        CarDTO carDto = buildCarDTO();
        CarVO carVO = new CarVO();
        carVO.setId(carDto.getId());
        carVO.setVin(carDto.getVin());
        carVO.setPrice(carDto.getPrice()); //装箱拆箱机制，不需要我们自己转化
        double totalPrice = carDto.getTotalPrice();
        DecimalFormat df = new DecimalFormat("#.00");
        String totalPriceStr = df.format(totalPrice);
        carVO.setTotalPrice(totalPriceStr);
        Date publishDate = carDto.getPublishDate();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String publishDateFormat = sdf.format(publishDate);
        carVO.setPublishDate(publishDateFormat);
        carVO.setBrandName(carDto.getBrand());
        carVO.setColor(carDto.getColor());
        List<PartDTO> partDTOS = carDto.getPartDTOS();
        boolean hasPart = partDTOS != null && !partDTOS.isEmpty();
        carVO.setHasPart(hasPart);
        DriverVO driverVO = new DriverVO();
        DriverDTO driverDTO = carDto.getDriverDTO();
        driverVO.setDriverId(driverDTO.getId());
        driverVO.setFullName(driverDTO.getName());
        carVO.setDriverVO(driverVO);
        System.out.println(carVO);
    }

    private CarDTO buildCarDTO() {
        CarDTO carDTO = new CarDTO();
        carDTO.setId(330L);
        carDTO.setVin("vin123456789");
        carDTO.setPrice(123789.126d);
        carDTO.setTotalPrice(143789.126d);
        carDTO.setPublishDate(new Date());
        carDTO.setBrand("大众");
        carDTO.setColor("白色");
//零件
        PartDTO partDTO1 = new PartDTO();
        partDTO1.setPartId(1L);
        partDTO1.setPartName("多功能方向盘");
        PartDTO partDTO2 = new PartDTO();
        partDTO2.setPartId(2L);
        partDTO2.setPartName("智能车门");
        List<PartDTO> partDTOList = new ArrayList<>();
        partDTOList.add(partDTO1);
        partDTOList.add(partDTO2);
        carDTO.setPartDTOS(partDTOList);
        //设置驾驶员
        DriverDTO driverDTO = new DriverDTO();
        driverDTO.setId(88L);
        driverDTO.setName("小明");
        carDTO.setDriverDTO(driverDTO);
        return carDTO;
    }

    @Test
    public void test2() {
        CarDTO carDto = buildCarDTO();
        CarVO carVO = CarConvert.INSTANCE.Dto2Vo(carDto);
        System.out.println(carVO);
    }

    /**
     * 测试mapstruct
     * List<CarDto>--> List<CarVo>
     */
    @Test
    public void test3() {
        CarDTO carDTO = buildCarDTO();
        List<CarDTO> carDTOList = new ArrayList<>();
        carDTOList.add(carDTO); // source
// target
        List<CarVO> carVOList = new ArrayList<>();
        for (CarDTO dto : carDTOList) {
            CarVO carVO = CarConvert.INSTANCE.Dto2Vo(carDTO);
            carVOList.add(carVO);
            System.out.println(carVOList);
        }
    }


    @Test
    public void test4() {
        CarDTO carDTO = buildCarDTO();
        List<CarDTO> carDTOList = new ArrayList<>();
        carDTOList.add(carDTO); // source
        List<CarVO> carVOList = CarConvert.INSTANCE.dtos2vos(carDTOList);
        System.out.println(carVOList);
    }

    /**
     * 测试@BeanMapping
     */
    @Test
    public void test5() {
        CarDTO carDTO = buildCarDTO();
        VehicleVO vehicleVO = CarConvert.INSTANCE.carDTO2vehicleVO(carDTO);
        System.out.println(vehicleVO);
    }

    /**
     * 测试@BeanMapping
     */
    @Test
    public void test6() {
        CarDTO carDTO = buildCarDTO();
        VehicleVO vehicleVO = CarConvert.INSTANCE.carDTO2vehicleVO(carDTO);
        CarDTO carDTO2 = new CarDTO();
//        carDTO2.setId(330L);
        carDTO2.setBrand("迈巴赫");
        //通过carDTO2的属 性值来更新已存在的vehicleVO对象
        CarConvert.INSTANCE.updateVehicleVO(carDTO2, vehicleVO);
        System.out.println(vehicleVO);
    }


    /**
     * 测试@InheritInverseConfiguration反向继 承
     */
    @Test
    public void test7() {
        VehicleVO vehicleVO = new VehicleVO();
        vehicleVO.setId(9999L);
        vehicleVO.setBrandName("别克");
        vehicleVO.setPrice(66554322d);
        CarDTO carDTO = CarConvert.INSTANCE.vehicleVO2CarDTO(vehicleVO);
        System.out.println(carDTO);
    }

    // @Autowired
    @Resource
    private CarConvert carConvert;

    /**
     * 测试mapstruct 和spring整合
     */
    @Test
    public void test8() {
        VehicleVO vehicleVO = new VehicleVO();
        vehicleVO.setId(9999L);
        vehicleVO.setBrandName("别克");
        vehicleVO.setPrice(66554322d);
        CarDTO carDTO = carConvert.vehicleVO2CarDTO(vehicleVO);
        System.out.println(carDTO);
    }
}