package com.tensquare.friend.service;

import com.tensquare.friend.dao.FriendDao;
import com.tensquare.friend.dao.NoFriendDao;
import com.tensquare.friend.pojo.Friend;
import com.tensquare.friend.pojo.NoFriend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 交友的业务逻辑层
 */
@Service
@Transactional
public class FriendService {

    @Autowired
    private FriendDao friendDao;

    @Autowired
    private NoFriendDao noFriendDao;

    /**
     * 添加好友
     * @param userid
     * @param friendid
     * @return
     */
    public int addFriend(String userid, String friendid) {
        //先判断userid到friendid是否有数据，有就重复添加，返回0
        Friend friend = friendDao.findByUseridAndFriendid(userid,friendid);
        if (friend != null){
            return 0;
        }
        //直接添加好友，让好友表userid到friendid方向的type为0
        friend = new Friend();
        friend.setUserid(userid);
        friend.setFriendid(friendid);
        friend.setIslike("0");
        friendDao.save(friend);
        //判断从friendid到userid是否有数据，如果有，把双方状态都改为1
        if (friendDao.findByUseridAndFriendid(friendid,userid) != null ){
            //把双方islike改为1
            friendDao.updateIslike("1",userid,friendid);
            friendDao.updateIslike("1",friendid,userid);
        }
        return 1;
    }

    /**
     * 添加非好友
     * @param userid
     * @param friendid
     * @return
     */
    public int addNoFriend(String userid, String friendid) {
        //判断是否已经是非好友
        NoFriend noFriend = noFriendDao.findByUseridAndFriendid(userid, friendid);
        if (noFriend != null){
            return 0;
        }
        noFriend = new NoFriend();
        noFriend.setUserid(userid);
        noFriend.setFriendid(friendid);
        noFriendDao.save(noFriend);
        return 1;
    }

    /**
     * 删除好友
     * @param userid
     * @param friendid
     */
    public void deleteFriend(String userid, String friendid) {
        //删除好友表中userid到friendid中数据
        friendDao.deleteFriend(userid,friendid);
        //更新friendid到userid的islike为0
        friendDao.updateIslike("0",friendid,userid);
        //非好友表中添加数据
        NoFriend noFriend = new NoFriend();
        noFriend.setUserid(userid);
        noFriend.setFriendid(friendid);
        noFriendDao.save(noFriend);
    }
}
