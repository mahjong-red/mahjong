package cn.mahjong.core.news;

import org.springframework.stereotype.Service;

import cn.mahjong.core.base.BaseServiceImpl;
import cn.mahjong.model.News;

@Service("newsService")
public class NewsServiceImpl extends BaseServiceImpl implements NewsService {

	@Override
	public void save1(News news) throws Exception {
		save(news);
		
		if (news.getTitle().equals("aa")) {
			throw new Exception("ceshi");
		}
		news.setAuthor("aa122");
		update(news);
	}
}
