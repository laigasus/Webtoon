package com.webtoon.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webtoon.domain.ListVO;
import com.webtoon.domain.NaverVO;
import com.webtoon.domain.ViewVO;
import com.webtoon.mapper.NaverMapper;

@Service("NaverService")
public class NaverServiceImpl implements NaverService {

	@Autowired
	private NaverMapper naverMapper;
	private WebtoonService webtoonService;

	////////////////////////////////////////////////////////////////

	@Override
	public ArrayList<NaverVO> listBoard(int YoIll) {
		// TODO Auto-generated method stub
		webtoonService.webtoonCrawling();
		return naverMapper.listBoard(YoIll);
	}

	@Override
	public ArrayList<NaverVO> searchBoard(String searchParam) {
		// TODO Auto-generated method stub
		webtoonService.webtoonCrawling();
		return naverMapper.searchBoard(searchParam);
	}

	@Override
	public ArrayList<ListVO> toonList(String URL, String id) {
		// TODO Auto-generated method stub
		webtoonService.webtoonListCrawling(URL, id);
		return naverMapper.toonList(URL, id);
	}

	@Override
	public ArrayList<ViewVO> toonView(String url) {
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
