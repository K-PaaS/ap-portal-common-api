package org.openpaas.paasta.portal.common.api.domain.commonCode;

import org.jinq.orm.stream.JinqStream;
import org.openpaas.paasta.portal.common.api.config.Constants;
import org.openpaas.paasta.portal.common.api.config.JinqSource;
import org.openpaas.paasta.portal.common.api.entity.portal.CodeDetail;
import org.openpaas.paasta.portal.common.api.entity.portal.CodeGroup;
import org.openpaas.paasta.portal.common.api.repository.portal.CodeDetailRepository;
import org.openpaas.paasta.portal.common.api.repository.portal.CodeGroupRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by SEJI on 2018-03-26.
 */
@Service
public class CommonCodeService {
    private final Logger logger = getLogger(this.getClass());

    @Autowired
    JinqSource jinqSource;

    @Autowired
    CodeDetailRepository codeDetailRepository;

    @Autowired
    CodeGroupRepository codeGroupRepository;

    /**
     * 공통코드 목록을 조회한다.
     *
     * @param codeDetail CodeDetail(검색조건)
     * @return Map(자바클래스)
     */

    public Map<String, Object> getCommonCodeDetail(CodeDetail codeDetail) {
        JinqStream<CodeDetail> streams = jinqSource.streamAllPortal(CodeDetail.class);
        String groudId = codeDetail.getGroupId();
        String key = codeDetail.getKey();

        if (null != key && !"".equals(key) && !"null".equals(key.toLowerCase())) {
            streams = streams.where(c -> c.getKey().equals(key));
        }
        if (null != groudId && !"".equals(groudId) && !"null".equals(groudId.toLowerCase())) {
            streams = streams.where(c -> c.getGroupId().equals(groudId));
        }

        streams = streams.sortedBy(c -> c.getOrder());

        List<Map<String, Object>> resultList = streams.map(x -> new HashMap<String, Object>() {{
            put("key", x.getKey());
            put("value", x.getValue());
            put("summary", x.getSummary());
        }}).collect(Collectors.toList());

        return new HashMap<String, Object>() {{
            put("list", resultList);
        }};

    }

    /**
     * 공통코드 목록을 조회한다.
     *
     * @param codeDetail,codeGroup CodeDetail,CodeGroup (모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String, Object> getCommonCodeJoinGroup(CodeDetail codeDetail, CodeGroup codeGroup) {

        //공통코드 상세 조회를 한다. ::  공통코드 상세 개수를 조회한다.
        JinqStream<CodeDetail> codeDetailJinqStream = jinqSource.streamAllPortal(CodeDetail.class);

        String key = codeDetail.getKey();
        String GroupId = codeDetail.getGroupId();

        if (null != key && !"".equals(key)) {
            codeDetailJinqStream = codeDetailJinqStream.where(c -> c.getKey().equals(key));
        }

        if (null != GroupId && !"".equals(GroupId)) {
            codeDetailJinqStream = codeDetailJinqStream.where(c -> c.getGroupId().equals(GroupId));
        }

        codeDetailJinqStream = codeDetailJinqStream.sortedBy(c -> c.getOrder());

        List<Map<String, Object>> resultList = codeDetailJinqStream.map(x -> new HashMap<String, Object>() {{
            put("key", x.getKey());
            put("orgKey", x.getKey());
            put("value", x.getValue());
            put("summary", x.getSummary());
            put("groupId", x.getGroupId());

            put("useYn", x.getUseYn());
            put("order", x.getOrder());
            put("created", x.getCreated());
            put("lastModified", x.getLastmodified());
            put("userId", x.getUserId());

            put("pageNo", x.getPageNo());
            put("pageSize", x.getPageSize());
            put("procType", x.getProcType());

        }}).collect(Collectors.toList());


        return (Map<String, Object>) resultList;
    }


    /**
     * 공통 코드 그룹을 등록한다.
     *
     * @param codeGroup CodeGroup (모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String,Object> insertDetailGroup(CodeGroup codeGroup) {
        codeGroupRepository.save(codeGroup);
        return new HashMap<String, Object>() {{
            put("RESULT", Constants.RESULT_STATUS_SUCCESS);
        }};
    }


    /**
     * 공통 코드을 등록한다.
     *
     * @param codeDetail CodeDetail (모델클래스)
     * @return Map(자바클래스)
     */
    public Map<String,Object> insertDetail(CodeDetail codeDetail) {
        int count = codeDetailRepository.countByGroupId(codeDetail.getGroupId());
        System.out.println(count);
        codeDetail.setOrder(count+1);
        codeDetailRepository.save(codeDetail);

        return new HashMap<String, Object>() {{
            put("RESULT", Constants.RESULT_STATUS_SUCCESS);
        }};
    }

    /**
     * 공통 코드 그룹을 수정한다.
     *
     * @param codeGroup CodeGroup (모델클래스)
     * @return Map(자바클래스)
     */
    public String updateCommonGroup(String id,CodeGroup codeGroup) {
        String resultStr = Constants.RESULT_STATUS_SUCCESS;

        if(codeGroupRepository.findAllById(id) != null) {
            codeGroup.setId(codeGroup.getId());
            codeGroup.setName(codeGroup.getName());
            codeGroup.setUserId(codeGroup.getUserId());
            codeGroupRepository.save(codeGroup);
        }else {
            resultStr = Constants.RESULT_STATUS_FAIL;
        }
        return resultStr;
    }

    /**
     * 공통 코드을 수정한다.
     *
     * @param codeDetail CodeDetail (모델클래스)
     * @return Map(자바클래스)
     */
    public String updateCommonDetail(int no, CodeDetail codeDetail) {
        String resultStr = Constants.RESULT_STATUS_SUCCESS;

        if(codeDetailRepository.findAllByNo(no) != null) {
            codeDetail.setKey(codeDetail.getKey());
            codeDetail.setValue(codeDetail.getValue());
            codeDetail.setGroupId(codeDetail.getGroupId());
            codeDetail.setUserId(codeDetail.getUserId());
            codeDetail.setOrder(codeDetail.getOrder());
            codeDetail.setUserId(codeDetail.getUserId());
            codeDetail.setOrgId(codeDetail.getOrgId());
            codeDetail.setOrgKey(codeDetail.getOrgKey());
            codeDetailRepository.save(codeDetail);
        }else {
            resultStr = Constants.RESULT_STATUS_FAIL;
        }
        return resultStr;
    }

    /**
     * 공통 코드 그룹을 삭제한다.
     *
     * @param id
     * * @return Map(자바클래스)
     */
    public Map<String,Object> deleteCommonGroup(String id) {
        codeGroupRepository.deleteById(id);
        return new HashMap<String, Object>() {{
            put("RESULT", Constants.RESULT_STATUS_SUCCESS);
        }};
    }

    /**
     * 공통 코드을 삭제한다.
     *
     * @param no
     * @return Map(자바클래스)
     */
    public Map<String,Object> deleteCommonDetail(int no) {
        codeDetailRepository.delete(no);
        return new HashMap<String, Object>() {{
            put("RESULT", Constants.RESULT_STATUS_SUCCESS);
        }};
    }


}
