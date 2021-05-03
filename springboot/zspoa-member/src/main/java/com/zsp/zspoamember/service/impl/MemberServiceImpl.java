package com.zsp.zspoamember.service.impl;

import com.zsp.zspoamember.entity.Member;
import com.zsp.zspoamember.mapper.MemberMapper;
import com.zsp.zspoamember.service.MemberService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author zsp
 * @since 2021-05-03
 */
@Service
public class MemberServiceImpl extends ServiceImpl<MemberMapper, Member> implements MemberService {

}
