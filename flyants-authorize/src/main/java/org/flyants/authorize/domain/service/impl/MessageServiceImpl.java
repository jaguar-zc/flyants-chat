package org.flyants.authorize.domain.service.impl;
import java.util.ArrayList;
import java.util.Date;

import org.flyants.authorize.configuration.PageResult;
import org.flyants.authorize.domain.entity.platform.People;
import org.flyants.authorize.domain.entity.platform.dynamic.Dynamic;
import org.flyants.authorize.domain.entity.platform.message.Conversation;
import org.flyants.authorize.domain.entity.platform.message.Message;
import org.flyants.authorize.domain.entity.platform.message.MessageUser;
import org.flyants.authorize.domain.message.MessageHandler;
import org.flyants.authorize.domain.message.MessageType;
import org.flyants.authorize.domain.repository.ConversationRepository;
import org.flyants.authorize.domain.repository.MessageRepository;
import org.flyants.authorize.domain.repository.MessageUserRepository;
import org.flyants.authorize.domain.repository.PeopleRepository;
import org.flyants.authorize.domain.service.MessageService;
import org.flyants.authorize.dto.app.DynamicDto;
import org.flyants.authorize.dto.app.PeopleSimpleDto;
import org.flyants.authorize.dto.app.PublishMessageDto;
import org.flyants.common.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
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
        message.setMessageType(messageType);

        message.setSendTime(new Date());
        message.setView(0);

        MessageHandler messageHandler = messageType.getMessageHandler();
        MessageHandler builder = messageHandler.builder(publishMessage.getBody());

        message.setBody(builder.toBody());

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
    public PeopleSimpleDto getPeopleSimpleInfo(String messageUserId) {
        Optional<MessageUser> messageUserOptional = messageUserRepository.findById(messageUserId);
        if(!messageUserOptional.isPresent()){
            throw new BusinessException("用户不存在");
        }

        String peopleId = messageUserOptional.get().getPeopleId();

        Optional<People> peopleOptional = peopleRepository.findById(peopleId);
        if(!peopleOptional.isPresent()){
            throw new BusinessException("用户不存在");
        }
        People people = peopleOptional.get();


        PeopleSimpleDto peopleSimpleDto = new PeopleSimpleDto();
        peopleSimpleDto.setId(people.getId());
        peopleSimpleDto.setNickName(people.getNickName());
        peopleSimpleDto.setEncodedPrincipal(people.getEncodedPrincipal());
        return peopleSimpleDto;
    }
}
