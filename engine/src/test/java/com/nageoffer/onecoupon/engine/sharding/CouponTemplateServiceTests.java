package com.nageoffer.onecoupon.engine.sharding;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.nageoffer.onecoupon.engine.dao.entity.CouponTemplateDO;
import com.nageoffer.onecoupon.engine.service.CouponTemplateService;
import jakarta.annotation.Resource;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * 验证优惠券模板单元测试
 * <p>
 * 作者：马丁
 * 加项目群：早加入就是优势！500人内部沟通群，分享的知识总有你需要的 <a href="https://t.zsxq.com/cw7b9" />
 * 开发时间：2025-09-23
 */
@SpringBootTest
public class CouponTemplateServiceTests {

    @Resource
    private CouponTemplateService couponTemplateService;

    /**
     * 验证跨库查询失败问题
     * 需要配合 com.nageoffer.onecoupon.merchant.admin.template.CouponTemplateTest 里添加 ID 为：1810714735922956999L 的数据
     */
    @Test
    void test() {
        List<Long> list1 = new ArrayList<>();
        list1.add(1810714735922956666L);
        list1.add(1810714735922956999L);
        List<Long> list2 = new ArrayList<>();
        list2.add(1810966706881941507L);
        list2.add(1970515494187642882L);
        LambdaQueryWrapper<CouponTemplateDO> queryWrapper = Wrappers.lambdaQuery(CouponTemplateDO.class)
                .in(CouponTemplateDO::getShopNumber, list1)
                .in(CouponTemplateDO::getId, list2);
        assertThrows(Throwable.class, () -> couponTemplateService.list(queryWrapper));
    }
}
