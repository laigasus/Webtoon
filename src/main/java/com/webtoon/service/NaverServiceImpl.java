package com.webtoon.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webtoon.domain.WebtoonListVO;
import com.webtoon.domain.WebtoonVO;
import com.webtoon.domain.WebtoonViewVO;
import com.webtoon.mapper.NaverMapper;

@Service("NaverService")
public class NaverServiceImpl implements NaverService {

	@Autowired
	private NaverMapper naverMapper;
	private WebtoonService webtoonService;

	////////////////////////////////////////////////////////////////

	@Override
	public ArrayList<WebtoonVO> listBoard(int YoIll) {
		// TODO Auto-generated method stub
		webtoonService.webtoonCrawling();
		return naverMapper.listBoard(YoIll);
	}

	@Override
	public ArrayList<WebtoonVO> searchBoard(String searchParam) {
		// TODO Auto-generated method stub
		webtoonService.webtoonCrawling();
		return naverMapper.searchBoard(searchParam);
	}

	@Override
	public ArrayList<WebtoonListVO> toonList(String URL, String id) {
		// TODO Auto-generated method stub
		webtoonService.webtoonListCrawling(URL, id);
		return naverMapper.toonList(URL, id);
	}

	@Override
	public ArrayList<WebtoonViewVO> toonView(String url) {
		// TODO Auto-generated method stub
		webtoonService.webtoonViewCrawling(url);
		return naverMapper.toonView(url);
	}

	@Override
	public ArrayList<String> toonInfo(String URL) {
		// TODO Auto-generated method stub
		return webtoonService.webtoonInfoCrawling(URL);
	}

}
