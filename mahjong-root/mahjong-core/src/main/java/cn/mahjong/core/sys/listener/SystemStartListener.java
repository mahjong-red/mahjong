package cn.mahjong.core.sys.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;

import cn.mahjong.core.security.MahjongSecurityMetadata;
import cn.mahjong.utils.SpringContextUtil;

public class SystemStartListener implements ApplicationListener<ContextRefreshedEvent> {

	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		MahjongSecurityMetadata mahjongSecurityMetadata = (MahjongSecurityMetadata) SpringContextUtil.getBean("mahjongSecurityMetadata");
		mahjongSecurityMetadata.initSecurityMeta();
	}
}
