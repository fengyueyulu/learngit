package cn.edu.scujcc.dao;

import java.util.List;
import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import cn.edu.scujcc.model.Channel;;
@Repository
public interface ChannelRepository extends MongoRepository<Channel, Integer>{

public List<Channel> findByQuality(String q);

public void deleteById(String i);

public Optional<Channel> findById(String channelId);

public List<Channel> findByTitle(String t);

public List<Channel> findByCommentsNull();
}