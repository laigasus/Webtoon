package com.webtoon.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webtoon.domain.WebtoonListVO;
import com.webtoon.domain.WebtoonVO;
import com.webtoon.domain.WebtoonViewVO;
import com.webtoon.mapper.NaverMapper;

@Service("naverService")
public class NaverServiceImpl implements NaverService {

	@Autowired
	private WebtoonService webtoonService;

	@Autowired
	private NaverMapper naverMapper;
	////////////////////////////////////////////////////////////////

	@Override
	public ArrayList<WebtoonVO> listBoard(int YoIll) {
		
		webtoonService.webtoonCrawling();
		System.out.println("네이버 서비스 실행");
		return naverMapper.listBoard(YoIll);
	}

	@Override
	public ArrayList<WebtoonVO> searchBoard(String searchParam) {
		webtoonService.webtoonCrawling();
		return naverMapper.searchBoard(searchParam);
	}

	@Override
	public ArrayList<WebtoonListVO> toonList(String URL, String id) {
		webtoonService.webtoonListCrawling(URL, id);
		return naverMapper.toonList(URL, id);
	}

	@Override
	public ArrayList<WebtoonViewVO> toonView(String url) {
		webtoonService.webtoonViewCrawling(url);
		return naverMapper.toonView(url);
	}

	@Override
	public ArrayList<String> toonInfo(String URL) {
		return webtoonService.webtoonInfoCrawling(URL);
	}

}
