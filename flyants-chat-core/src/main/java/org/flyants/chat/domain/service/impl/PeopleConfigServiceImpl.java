package org.flyants.chat.domain.service.impl;

import org.flyants.chat.domain.entity.platform.PeopleConfig;
import org.flyants.chat.domain.entity.platform.system.DefaultPeopleConfig;
import org.flyants.chat.domain.repository.DefaultPeopleConfigRepository;
import org.flyants.chat.domain.repository.PeopleConfigRepository;
import org.flyants.chat.domain.service.PeopleConfigService;
import org.flyants.chat.dto.app.PeopleConfigDto;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * @Author zhangchao
 * @Date 2019/6/6 12:40
 * @Version v1.0
 */
@Service
public class PeopleConfigServiceImpl implements PeopleConfigService {

    @Autowired
    PeopleConfigRepository peopleConfigRepository;

    @Autowired
    DefaultPeopleConfigRepository defaultPeopleConfigRepository;

    @Override
    public PeopleConfig findFirstByPeopleId(String peopleId) {
        return Optional.ofNullable(peopleConfigRepository.findFirstByPeopleId(peopleId))
                .map(item -> {
                    PeopleConfig config = new PeopleConfig();
                    if(item.isPresent()){
                        config = item.get();
                    }else{
                        DefaultPeopleConfig first = findFirst();
                        BeanUtils.copyProperties(first,config);
                        config.setPeopleId(peopleId);
                        peopleConfigRepository.save(config);
                    }
                    return config;
                }).get();
    }


    @Override
    public void updatePeopleConfig(String peopleId, PeopleConfigDto peopleConfig) {

        PeopleConfig config = findFirstByPeopleId(peopleId);


        if(peopleConfig.getUsePhonePlusMe() != null){
            config.setUsePhonePlusMe(peopleConfig.getUsePhonePlusMe());
        }
        if(peopleConfig.getUseChatNoPlusMe() != null){
            config.setUseChatNoPlusMe(peopleConfig.getUseChatNoPlusMe());
        }
        if(peopleConfig.getUseQrCodePlusMe() != null){
            config.setUseQrCodePlusMe(peopleConfig.getUseQrCodePlusMe());
        }

        if(peopleConfig.getAddMeVerify() != null){
            config.setAddMeVerify(peopleConfig.getAddMeVerify());
        }
        if(peopleConfig.getAllowTomeRecommendedGroup() != null){
            config.setAllowTomeRecommendedGroup(peopleConfig.getAllowTomeRecommendedGroup());
        }
        if(peopleConfig.getChatRecordCloudStore() != null){
            config.setChatRecordCloudStore(peopleConfig.getChatRecordCloudStore());
        }
        if(peopleConfig.getDynameicVideoPlayNet() != null){
            config.setDynameicVideoPlayNet(peopleConfig.getDynameicVideoPlayNet());
        }
        if(peopleConfig.getMessageNotifyShake() != null){
            config.setMessageNotifyShake(peopleConfig.getMessageNotifyVoice());
        }
        if(peopleConfig.getMessageNotifyVoice() != null){
            config.setMessageNotifyVoice(peopleConfig.getMessageNotifyVoice());
        }

        peopleConfigRepository.saveAndFlush(config);

    }



    public DefaultPeopleConfig findFirst(){
       return Optional.ofNullable(defaultPeopleConfigRepository.findAll().stream().findFirst())
               .map(item ->{
                   DefaultPeopleConfig defaultPeopleConfig = new DefaultPeopleConfig();
                    if(item.isPresent()){
                        defaultPeopleConfig = item.get();
                    }else{
                        defaultPeopleConfig = new DefaultPeopleConfig();
                        defaultPeopleConfig.setChatRecordCloudStore(0);
                        defaultPeopleConfig.setMessageNotifyVoice(0);
                        defaultPeopleConfig.setMessageNotifyShake(0);
                        defaultPeopleConfig.setUseChatNoPlusMe(0);
                        defaultPeopleConfig.setUsePhonePlusMe(0);
                        defaultPeopleConfig.setUseQrCodePlusMe(0);
                        defaultPeopleConfig.setAddMeVerify(0);
                        defaultPeopleConfig.setAllowTomeRecommendedGroup(0);
                        defaultPeopleConfig.setDynameicVideoPlayNet(0);
                        defaultPeopleConfigRepository.save(defaultPeopleConfig);
                    }

                   return defaultPeopleConfig;
               }).get();
    }
}
