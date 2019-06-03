package org.flyants.authorize.domain.service.impl;

import org.flyants.authorize.configuration.PageResult;
import org.flyants.authorize.domain.entity.platform.People;
import org.flyants.authorize.domain.entity.platform.comments.Comments;
import org.flyants.authorize.domain.entity.platform.comments.CommentsType;
import org.flyants.authorize.domain.repository.CommentsRepository;
import org.flyants.authorize.domain.service.CommentsService;
import org.flyants.authorize.domain.service.PeopleService;
import org.flyants.authorize.dto.app.CommentsPublishDto;
import org.flyants.authorize.dto.app.CommentsSimpleDto;
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
import java.util.stream.Collectors;

/**
 * @Author zhangchao
 * @Date 2019/5/28 10:04
 * @Version v1.0
 */
@Service
public class CommentsServiceImpl implements CommentsService {


    @Autowired
    CommentsRepository commentsRepository;

    @Autowired
    PeopleService peopleService;


    @Override
    public void publish(String peopleId, CommentsPublishDto publishDto) {
        Comments comments = new Comments();
        comments.setCreateTime(new Date());
        comments.setResourceId(publishDto.getResourceId());
        comments.setLevel(publishDto.getCommentsId() == null?1:2);
        comments.setCommentsId(publishDto.getCommentsId());
        comments.setText(publishDto.getText());
        comments.setCommentsType(publishDto.getCommentsType());
        comments.setPeopleId(peopleId);

        Optional<People> optionalPeople = peopleService.findPeopleById(peopleId);
        if(optionalPeople.isPresent()){
            People people = optionalPeople.get();
            comments.setNickName(people.getNickName());
            comments.setEncodedPrincipal(people.getEncodedPrincipal());

        }

        commentsRepository.save(comments);
    }


    @Override
    public PageResult<CommentsSimpleDto> list(Integer page, Integer size, CommentsType commentsType, String resourceId) {
        PageRequest of = PageRequest.of(page - 1, size, Sort.by(Sort.Order.asc("createTime")));
        Specification spec = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery query, CriteriaBuilder cb) {
                List<Predicate> pr = new ArrayList<>();
                pr.add(cb.equal( root.get("commentsType").as(CommentsType.class),commentsType));
                pr.add(cb.equal( root.get("resourceId").as(String.class),resourceId));

                return cb.and(pr.toArray(new Predicate[pr.size()]));
            }
        };
        Page<Comments> all = commentsRepository.findAll(spec, of);
        long totalElements = all.getTotalElements();
        List<Comments> content = all.getContent();

        List<CommentsSimpleDto> collect = content.stream().map(item -> {
            CommentsSimpleDto commentsSimpleDto = new CommentsSimpleDto();
            commentsSimpleDto.setId(item.getId());
            commentsSimpleDto.setResourceId(item.getResourceId());
            commentsSimpleDto.setCreateTime(item.getCreateTime());
            commentsSimpleDto.setText(item.getText());
            commentsSimpleDto.setLevel(item.getLevel());
            commentsSimpleDto.setNickName(item.getNickName());
            commentsSimpleDto.setEncodedPrincipal(item.getEncodedPrincipal());

            if(item.getLevel() == 2 && item.getCommentsId() != null){
                Optional<Comments> replyComments = commentsRepository.findById(item.getCommentsId());
                if (replyComments.isPresent()) {
                    commentsSimpleDto.setReplyNickName(replyComments.get().getNickName());
                }
            }

            return commentsSimpleDto;
        }).filter(item -> item != null).collect(Collectors.toList());


        return new PageResult<CommentsSimpleDto>(totalElements, collect);
    }
}
