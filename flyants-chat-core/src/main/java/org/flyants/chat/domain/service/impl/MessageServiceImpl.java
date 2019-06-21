package org.flyants.chat.domain.service.impl;

import org.flyants.chat.configuration.PageResult;
import org.flyants.chat.domain.entity.platform.People;
import org.flyants.chat.domain.entity.platform.message.Conversation;
import org.flyants.chat.domain.entity.platform.message.Message;
import org.flyants.chat.domain.entity.platform.message.MessageUser;
import org.flyants.chat.domain.message.MessageType;
import org.flyants.chat.domain.repository.ConversationRepository;
import org.flyants.chat.domain.repository.MessageRepository;
import org.flyants.chat.domain.repository.MessageUserRepository;
import org.flyants.chat.domain.repository.PeopleRepository;
import org.flyants.chat.domain.service.MessageService;
import org.flyants.chat.domain.service.OssObjectServie;
import org.flyants.chat.dto.app.MessageUserSimpleInfoDto;
import org.flyants.chat.dto.app.PublishMessageDto;
import org.flyants.common.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

/**
 * @Author zhangchao
 * @Date 2019/5/24 16:22
 * @Version v1.0
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    ConversationRepository conversationRepository;

    @Autowired
    MessageRepository messageRepository;

    @Autowired
    PeopleRepository peopleRepository;

    @Autowired
    MessageUserRepository messageUserRepository;


    @Autowired
    OssObjectServie ossObjectServie;

    @Override
    public void publishMessage(String peopleId, PublishMessageDto publishMessage) {
        MessageUser messageUser = messageUserRepository.findByPeopleId(peopleId);
        String selfMessageUserId = messageUser.getId();

        Optional<Conversation> conversationOptional = conversationRepository.findById(publishMessage.getConversationId());

        if (!conversationOptional.isPresent()) {
            throw new BusinessException("会话不存在");
        }
        Conversation conversation = conversationOptional.get();

        MessageType messageType = publishMessage.getMessageType();

        Message message = new Message();
        message.setConversationId(conversation.getId());
        message.setMessageUserId(selfMessageUserId);
        message.setBody(publishMessage.getBody());
        message.setMessageType(messageType);
        message.setSendTime(new Date());
        message.setView(0);
        messageRepository.save(message);


        // todo 推送消息
    }


    @Override
    public PageResult<Message> list(Integer page, Integer size, String conversationId) {

        PageRequest of = PageRequest.of(page - 1, size,  Sort.by(Sort.Order.desc("sendTime")));
        Specification spec = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
                List<Predicate> pr = new ArrayList<>();
                pr.add(cb.equal(root.get("conversationId").as(String.class),conversationId));
                return cb.and(pr.toArray(new Predicate[pr.size()]));
            }
        };

        Page<Message> all = messageRepository.findAll(spec, of);
        long totalElements = all.getTotalElements();
        List<Message> content = all.getContent();
        return new PageResult(totalElements,content);
    }

    @Override
    public MessageUserSimpleInfoDto getPeopleSimpleInfo(String messageUserId) {
        Optional<MessageUser> messageUserOptional = messageUserRepository.findById(messageUserId);
        if(!messageUserOptional.isPresent()){
            throw new BusinessException("用户不存在");
        }
        MessageUser messageUser = messageUserOptional.get();

        MessageUserSimpleInfoDto peopleSimpleDto = new MessageUserSimpleInfoDto();
        peopleSimpleDto.setId(messageUser.getId());
        peopleSimpleDto.setNickName(messageUser.getNickName());
        peopleSimpleDto.setEncodedPrincipal(messageUser.getEncodedPrincipal());


        //todo 要删除的
        if(peopleSimpleDto.getEncodedPrincipal().contains("@")){
            String path = ossObjectServie.generateUserIcon("headimg", peopleSimpleDto.getNickName());
            messageUser.setEncodedPrincipal(path);
            messageUserRepository.saveAndFlush(messageUser);
            Optional<People> optionalPeople = peopleRepository.findById(messageUser.getPeopleId());
            People people = optionalPeople.get();
            people.setEncodedPrincipal(path);
            peopleRepository.saveAndFlush(people);
        }



        return peopleSimpleDto;
    }
}
