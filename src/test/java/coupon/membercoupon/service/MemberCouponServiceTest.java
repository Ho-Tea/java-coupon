package coupon.membercoupon.service;

import java.time.LocalDateTime;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;
import coupon.coupons.domain.Category;
import coupon.coupons.domain.Coupon;
import coupon.coupons.repository.CouponRepository;
import coupon.coupons.service.CouponService;
import coupon.member.domain.Member;
import coupon.member.repository.MemberRepository;
import coupon.membercoupon.domain.MemberCoupon;
import coupon.membercoupon.repository.MemberCouponRepository;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Sql(scripts = "/schema.sql")
class MemberCouponServiceTest {

    @Autowired
    private MemberCouponService memberCouponService;
    @Autowired
    private CouponService couponService;
    @Autowired
    private MemberCouponRepository memberCouponRepository;
    @Autowired
    private MemberRepository memberRepository;
    @Autowired
    private CouponRepository couponRepository;


    @DisplayName("회원 쿠폰 발급에 성공한다.")
    @Test
    void issue() {
        Member member = memberRepository.save(new Member("회원"));
        Coupon coupon = couponRepository.save(new Coupon("유효한 쿠폰", 1000, 5000, Category.FASHION.name(), LocalDateTime.now(), LocalDateTime.now()));
        MemberCoupon saved = memberCouponService.issue(member, coupon);

        assertThat(memberCouponRepository.findById(saved.getId())).isNotNull();
    }

    @DisplayName("회원 쿠폰 조회에 성공한다.")
    @Test
    void findAllBy() {
        Member member = memberRepository.save(new Member("회원"));
        Coupon coupon1 = couponService.create(new Coupon("유효한 쿠폰1", 1000, 5000, Category.FASHION.name(), LocalDateTime.now(), LocalDateTime.now()));
        Coupon coupon2 = couponService.create(new Coupon("유효한 쿠폰2", 1000, 5000, Category.FASHION.name(), LocalDateTime.now(), LocalDateTime.now()));
        memberCouponRepository.save(new MemberCoupon(coupon1, member, LocalDateTime.now()));
        memberCouponRepository.save(new MemberCoupon(coupon2, member, LocalDateTime.now()));

        assertThat(memberCouponService.findAllBy(member).size()).isEqualTo(2);
    }
}
