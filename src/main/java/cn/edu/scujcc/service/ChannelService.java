package cn.edu.scujcc.service;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import cn.edu.scujcc.dao.ChannelRepository;
import cn.edu.scujcc.model.*;


@Service

public class ChannelService {
	@Resource
	private ChannelRepository repo;
	public ChannelService() {

	}

	/**
	 * 获取频道所有数据
	 * 
	 * @return
	 */
	public List<Channel> getAllChannels() {
		return repo.findAll();
	}

	public Channel getChannel(String channelId) {
		Optional<Channel> result = repo.findById(channelId);
		if (result.isPresent()) {
			return result.get();
		} else {
			return null;
		}

	}

	/**
	 * 
	 * 
	 * 删除频道
	 */

	@SuppressWarnings("unused")
	public boolean deleteChannel(String id) {

		boolean result = true;
		repo.deleteById(id);

		return true;
	}

	@SuppressWarnings("unused")
	public Channel createChannel(Channel c) {
		return repo.save(c);

	}
/**
 * ge
 * @param c
 * @return
 */
	public Channel updateChannel(Channel c) {
		Channel saved = getChannel(c.getId());
		if (c.getTitle() != null) {
			saved.setTitle(c.getTitle());
		}
		if (c.getQuality() != null) {
			saved.setQuality(c.getQuality());
		}
		if (c.getUrl() != null) {
			saved.setUrl(c.getUrl());
		}
		//
		if(c.getComments()!=null) {
			if(saved.getComments()!=null) {
				saved.getComments().addAll(c.getComments());
				}else {
			saved.setComments(c.getComments());
				}
		}
		return repo.save(saved);// 保存更新后的实体对象
	}

	// 更新频道信息
	// c 新的频道信息
	public Channel renewChannel(Channel c) {

		return repo.save(c);
	}

	public List<Channel> searchByQuality(String quality) {
		return repo.findByQuality(quality);
	}

	public List<Channel> searchByTitle(String title) {
		return repo.findByTitle(title);

	}
	/**
	 * 获得冷门频道
	 */
	public List<Channel> findColdChannels() {
		// TODO Auto-generated method stub
		return repo.findByCommentsNull();
	}
	public List<Channel> findChannelPage(int page) {
		// TODO Auto-generated method stub
		Page<Channel> p = repo.findAll(PageRequest.of(page, 3));
		return p.toList();
	}

}
