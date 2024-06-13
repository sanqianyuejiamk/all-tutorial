package com.mengka.springboot.dao.domain;

import com.mengka.springboot.model.Page;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author huangyy
 * @description
 * @date 2017/04/30.
 */
public class DSTelVoiceCallExample {
    protected String orderByClause;
    protected boolean distinct;
    protected List<Criteria> oredCriteria = new ArrayList();
    protected Page page;

    public DSTelVoiceCallExample() {
    }

    public void setOrderByClause(String orderByClause) {
        this.orderByClause = orderByClause;
    }

    public String getOrderByClause() {
        return this.orderByClause;
    }

    public void setDistinct(boolean distinct) {
        this.distinct = distinct;
    }

    public boolean isDistinct() {
        return this.distinct;
    }

    public List<DSTelVoiceCallExample.Criteria> getOredCriteria() {
        return this.oredCriteria;
    }

    public void or(DSTelVoiceCallExample.Criteria criteria) {
        this.oredCriteria.add(criteria);
    }

    public DSTelVoiceCallExample.Criteria or() {
        DSTelVoiceCallExample.Criteria criteria = this.createCriteriaInternal();
        this.oredCriteria.add(criteria);
        return criteria;
    }

    public DSTelVoiceCallExample.Criteria createCriteria() {
        DSTelVoiceCallExample.Criteria criteria = this.createCriteriaInternal();
        if(this.oredCriteria.size() == 0) {
            this.oredCriteria.add(criteria);
        }

        return criteria;
    }

    protected DSTelVoiceCallExample.Criteria createCriteriaInternal() {
        DSTelVoiceCallExample.Criteria criteria = new DSTelVoiceCallExample.Criteria();
        return criteria;
    }

    public void clear() {
        this.oredCriteria.clear();
        this.orderByClause = null;
        this.distinct = false;
    }

    public void setPage(Page page) {
        this.page = page;
    }

    public Page getPage() {
        return this.page;
    }

    public static class Criteria extends DSTelVoiceCallExample.GeneratedCriteria {
        protected Criteria() {
        }

        public DSTelVoiceCallExample.Criteria andTenantIdLikeInsensitive(String value) {
            this.addCriterion("upper(Tenant_Id) like", value.toUpperCase(), "tenantId");
            return this;
        }

        public DSTelVoiceCallExample.Criteria andUserIdLikeInsensitive(String value) {
            this.addCriterion("upper(User_Id) like", value.toUpperCase(), "userId");
            return this;
        }

        public DSTelVoiceCallExample.Criteria andMobileLikeInsensitive(String value) {
            this.addCriterion("upper(Mobile) like", value.toUpperCase(), "mobile");
            return this;
        }

        public DSTelVoiceCallExample.Criteria andPeerNumberLikeInsensitive(String value) {
            this.addCriterion("upper(Peer_Number) like", value.toUpperCase(), "peerNumber");
            return this;
        }

        public DSTelVoiceCallExample.Criteria andLocationLikeInsensitive(String value) {
            this.addCriterion("upper(Location) like", value.toUpperCase(), "location");
            return this;
        }

        public DSTelVoiceCallExample.Criteria andLocationTypeLikeInsensitive(String value) {
            this.addCriterion("upper(Location_Type) like", value.toUpperCase(), "locationType");
            return this;
        }

        public DSTelVoiceCallExample.Criteria andPeerCarrierLikeInsensitive(String value) {
            this.addCriterion("upper(Peer_Carrier) like", value.toUpperCase(), "peerCarrier");
            return this;
        }

        public DSTelVoiceCallExample.Criteria andDialTypeLikeInsensitive(String value) {
            this.addCriterion("upper(Dial_Type) like", value.toUpperCase(), "dialType");
            return this;
        }

        public DSTelVoiceCallExample.Criteria andCalledProvLikeInsensitive(String value) {
            this.addCriterion("upper(called_prov) like", value.toUpperCase(), "calledProv");
            return this;
        }

        public DSTelVoiceCallExample.Criteria andCalledCityLikeInsensitive(String value) {
            this.addCriterion("upper(called_city) like", value.toUpperCase(), "calledCity");
            return this;
        }

        public DSTelVoiceCallExample.Criteria andTaskIdLikeInsensitive(String value) {
            this.addCriterion("upper(TASK_ID) like", value.toUpperCase(), "taskId");
            return this;
        }
    }

    public static class Criterion {
        private String condition;
        private Object value;
        private Object secondValue;
        private boolean noValue;
        private boolean singleValue;
        private boolean betweenValue;
        private boolean listValue;
        private String typeHandler;

        public String getCondition() {
            return this.condition;
        }

        public Object getValue() {
            return this.value;
        }

        public Object getSecondValue() {
            return this.secondValue;
        }

        public boolean isNoValue() {
            return this.noValue;
        }

        public boolean isSingleValue() {
            return this.singleValue;
        }

        public boolean isBetweenValue() {
            return this.betweenValue;
        }

        public boolean isListValue() {
            return this.listValue;
        }

        public String getTypeHandler() {
            return this.typeHandler;
        }

        protected Criterion(String condition) {
            this.condition = condition;
            this.typeHandler = null;
            this.noValue = true;
        }

        protected Criterion(String condition, Object value, String typeHandler) {
            this.condition = condition;
            this.value = value;
            this.typeHandler = typeHandler;
            if(value instanceof List) {
                this.listValue = true;
            } else {
                this.singleValue = true;
            }

        }

        protected Criterion(String condition, Object value) {
            this(condition, value, (String)null);
        }

        protected Criterion(String condition, Object value, Object secondValue, String typeHandler) {
            this.condition = condition;
            this.value = value;
            this.secondValue = secondValue;
            this.typeHandler = typeHandler;
            this.betweenValue = true;
        }

        protected Criterion(String condition, Object value, Object secondValue) {
            this(condition, value, secondValue, (String)null);
        }
    }

    protected abstract static class GeneratedCriteria {
        protected List<DSTelVoiceCallExample.Criterion> criteria = new ArrayList();

        protected GeneratedCriteria() {
        }

        public boolean isValid() {
            return this.criteria.size() > 0;
        }

        public List<DSTelVoiceCallExample.Criterion> getAllCriteria() {
            return this.criteria;
        }

        public List<DSTelVoiceCallExample.Criterion> getCriteria() {
            return this.criteria;
        }

        protected void addCriterion(String condition) {
            if(condition == null) {
                throw new RuntimeException("Value for condition cannot be null");
            } else {
                this.criteria.add(new DSTelVoiceCallExample.Criterion(condition));
            }
        }

        protected void addCriterion(String condition, Object value, String property) {
            if(value == null) {
                throw new RuntimeException("Value for " + property + " cannot be null");
            } else {
                this.criteria.add(new DSTelVoiceCallExample.Criterion(condition, value));
            }
        }

        protected void addCriterion(String condition, Object value1, Object value2, String property) {
            if(value1 != null && value2 != null) {
                this.criteria.add(new DSTelVoiceCallExample.Criterion(condition, value1, value2));
            } else {
                throw new RuntimeException("Between values for " + property + " cannot be null");
            }
        }

        public DSTelVoiceCallExample.Criteria andTenantIdIsNull() {
            this.addCriterion("Tenant_Id is null");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andTenantIdIsNotNull() {
            this.addCriterion("Tenant_Id is not null");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andTenantIdEqualTo(String value) {
            this.addCriterion("Tenant_Id =", value, "tenantId");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andTenantIdNotEqualTo(String value) {
            this.addCriterion("Tenant_Id <>", value, "tenantId");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andTenantIdGreaterThan(String value) {
            this.addCriterion("Tenant_Id >", value, "tenantId");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andTenantIdGreaterThanOrEqualTo(String value) {
            this.addCriterion("Tenant_Id >=", value, "tenantId");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andTenantIdLessThan(String value) {
            this.addCriterion("Tenant_Id <", value, "tenantId");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andTenantIdLessThanOrEqualTo(String value) {
            this.addCriterion("Tenant_Id <=", value, "tenantId");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andTenantIdLike(String value) {
            this.addCriterion("Tenant_Id like", value, "tenantId");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andTenantIdNotLike(String value) {
            this.addCriterion("Tenant_Id not like", value, "tenantId");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andTenantIdIn(List<String> values) {
            this.addCriterion("Tenant_Id in", values, "tenantId");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andTenantIdNotIn(List<String> values) {
            this.addCriterion("Tenant_Id not in", values, "tenantId");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andTenantIdBetween(String value1, String value2) {
            this.addCriterion("Tenant_Id between", value1, value2, "tenantId");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andTenantIdNotBetween(String value1, String value2) {
            this.addCriterion("Tenant_Id not between", value1, value2, "tenantId");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andUserIdIsNull() {
            this.addCriterion("User_Id is null");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andUserIdIsNotNull() {
            this.addCriterion("User_Id is not null");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andUserIdEqualTo(String value) {
            this.addCriterion("User_Id =", value, "userId");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andUserIdNotEqualTo(String value) {
            this.addCriterion("User_Id <>", value, "userId");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andUserIdGreaterThan(String value) {
            this.addCriterion("User_Id >", value, "userId");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andUserIdGreaterThanOrEqualTo(String value) {
            this.addCriterion("User_Id >=", value, "userId");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andUserIdLessThan(String value) {
            this.addCriterion("User_Id <", value, "userId");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andUserIdLessThanOrEqualTo(String value) {
            this.addCriterion("User_Id <=", value, "userId");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andUserIdLike(String value) {
            this.addCriterion("User_Id like", value, "userId");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andUserIdNotLike(String value) {
            this.addCriterion("User_Id not like", value, "userId");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andUserIdIn(List<String> values) {
            this.addCriterion("User_Id in", values, "userId");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andUserIdNotIn(List<String> values) {
            this.addCriterion("User_Id not in", values, "userId");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andUserIdBetween(String value1, String value2) {
            this.addCriterion("User_Id between", value1, value2, "userId");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andUserIdNotBetween(String value1, String value2) {
            this.addCriterion("User_Id not between", value1, value2, "userId");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andMobileIsNull() {
            this.addCriterion("Mobile is null");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andMobileIsNotNull() {
            this.addCriterion("Mobile is not null");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andMobileEqualTo(String value) {
            this.addCriterion("Mobile =", value, "mobile");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andMobileNotEqualTo(String value) {
            this.addCriterion("Mobile <>", value, "mobile");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andMobileGreaterThan(String value) {
            this.addCriterion("Mobile >", value, "mobile");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andMobileGreaterThanOrEqualTo(String value) {
            this.addCriterion("Mobile >=", value, "mobile");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andMobileLessThan(String value) {
            this.addCriterion("Mobile <", value, "mobile");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andMobileLessThanOrEqualTo(String value) {
            this.addCriterion("Mobile <=", value, "mobile");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andMobileLike(String value) {
            this.addCriterion("Mobile like", value, "mobile");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andMobileNotLike(String value) {
            this.addCriterion("Mobile not like", value, "mobile");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andMobileIn(List<String> values) {
            this.addCriterion("Mobile in", values, "mobile");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andMobileNotIn(List<String> values) {
            this.addCriterion("Mobile not in", values, "mobile");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andMobileBetween(String value1, String value2) {
            this.addCriterion("Mobile between", value1, value2, "mobile");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andMobileNotBetween(String value1, String value2) {
            this.addCriterion("Mobile not between", value1, value2, "mobile");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andTimeIsNull() {
            this.addCriterion("Time is null");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andTimeIsNotNull() {
            this.addCriterion("Time is not null");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andTimeEqualTo(Date value) {
            this.addCriterion("Time =", value, "time");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andTimeNotEqualTo(Date value) {
            this.addCriterion("Time <>", value, "time");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andTimeGreaterThan(Date value) {
            this.addCriterion("Time >", value, "time");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andTimeGreaterThanOrEqualTo(Date value) {
            this.addCriterion("Time >=", value, "time");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andTimeLessThan(Date value) {
            this.addCriterion("Time <", value, "time");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andTimeLessThanOrEqualTo(Date value) {
            this.addCriterion("Time <=", value, "time");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andTimeIn(List<Date> values) {
            this.addCriterion("Time in", values, "time");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andTimeNotIn(List<Date> values) {
            this.addCriterion("Time not in", values, "time");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andTimeBetween(Date value1, Date value2) {
            this.addCriterion("Time between", value1, value2, "time");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andTimeNotBetween(Date value1, Date value2) {
            this.addCriterion("Time not between", value1, value2, "time");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andPeerNumberIsNull() {
            this.addCriterion("Peer_Number is null");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andPeerNumberIsNotNull() {
            this.addCriterion("Peer_Number is not null");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andPeerNumberEqualTo(String value) {
            this.addCriterion("Peer_Number =", value, "peerNumber");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andPeerNumberNotEqualTo(String value) {
            this.addCriterion("Peer_Number <>", value, "peerNumber");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andPeerNumberGreaterThan(String value) {
            this.addCriterion("Peer_Number >", value, "peerNumber");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andPeerNumberGreaterThanOrEqualTo(String value) {
            this.addCriterion("Peer_Number >=", value, "peerNumber");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andPeerNumberLessThan(String value) {
            this.addCriterion("Peer_Number <", value, "peerNumber");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andPeerNumberLessThanOrEqualTo(String value) {
            this.addCriterion("Peer_Number <=", value, "peerNumber");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andPeerNumberLike(String value) {
            this.addCriterion("Peer_Number like", value, "peerNumber");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andPeerNumberNotLike(String value) {
            this.addCriterion("Peer_Number not like", value, "peerNumber");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andPeerNumberIn(List<String> values) {
            this.addCriterion("Peer_Number in", values, "peerNumber");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andPeerNumberNotIn(List<String> values) {
            this.addCriterion("Peer_Number not in", values, "peerNumber");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andPeerNumberBetween(String value1, String value2) {
            this.addCriterion("Peer_Number between", value1, value2, "peerNumber");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andPeerNumberNotBetween(String value1, String value2) {
            this.addCriterion("Peer_Number not between", value1, value2, "peerNumber");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andLocationIsNull() {
            this.addCriterion("Location is null");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andLocationIsNotNull() {
            this.addCriterion("Location is not null");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andLocationEqualTo(String value) {
            this.addCriterion("Location =", value, "location");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andLocationNotEqualTo(String value) {
            this.addCriterion("Location <>", value, "location");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andLocationGreaterThan(String value) {
            this.addCriterion("Location >", value, "location");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andLocationGreaterThanOrEqualTo(String value) {
            this.addCriterion("Location >=", value, "location");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andLocationLessThan(String value) {
            this.addCriterion("Location <", value, "location");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andLocationLessThanOrEqualTo(String value) {
            this.addCriterion("Location <=", value, "location");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andLocationLike(String value) {
            this.addCriterion("Location like", value, "location");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andLocationNotLike(String value) {
            this.addCriterion("Location not like", value, "location");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andLocationIn(List<String> values) {
            this.addCriterion("Location in", values, "location");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andLocationNotIn(List<String> values) {
            this.addCriterion("Location not in", values, "location");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andLocationBetween(String value1, String value2) {
            this.addCriterion("Location between", value1, value2, "location");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andLocationNotBetween(String value1, String value2) {
            this.addCriterion("Location not between", value1, value2, "location");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andLocationTypeIsNull() {
            this.addCriterion("Location_Type is null");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andLocationTypeIsNotNull() {
            this.addCriterion("Location_Type is not null");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andLocationTypeEqualTo(String value) {
            this.addCriterion("Location_Type =", value, "locationType");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andLocationTypeNotEqualTo(String value) {
            this.addCriterion("Location_Type <>", value, "locationType");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andLocationTypeGreaterThan(String value) {
            this.addCriterion("Location_Type >", value, "locationType");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andLocationTypeGreaterThanOrEqualTo(String value) {
            this.addCriterion("Location_Type >=", value, "locationType");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andLocationTypeLessThan(String value) {
            this.addCriterion("Location_Type <", value, "locationType");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andLocationTypeLessThanOrEqualTo(String value) {
            this.addCriterion("Location_Type <=", value, "locationType");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andLocationTypeLike(String value) {
            this.addCriterion("Location_Type like", value, "locationType");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andLocationTypeNotLike(String value) {
            this.addCriterion("Location_Type not like", value, "locationType");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andLocationTypeIn(List<String> values) {
            this.addCriterion("Location_Type in", values, "locationType");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andLocationTypeNotIn(List<String> values) {
            this.addCriterion("Location_Type not in", values, "locationType");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andLocationTypeBetween(String value1, String value2) {
            this.addCriterion("Location_Type between", value1, value2, "locationType");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andLocationTypeNotBetween(String value1, String value2) {
            this.addCriterion("Location_Type not between", value1, value2, "locationType");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andDurationIsNull() {
            this.addCriterion("Duration is null");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andDurationIsNotNull() {
            this.addCriterion("Duration is not null");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andDurationEqualTo(Integer value) {
            this.addCriterion("Duration =", value, "duration");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andDurationNotEqualTo(Integer value) {
            this.addCriterion("Duration <>", value, "duration");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andDurationGreaterThan(Integer value) {
            this.addCriterion("Duration >", value, "duration");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andDurationGreaterThanOrEqualTo(Integer value) {
            this.addCriterion("Duration >=", value, "duration");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andDurationLessThan(Integer value) {
            this.addCriterion("Duration <", value, "duration");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andDurationLessThanOrEqualTo(Integer value) {
            this.addCriterion("Duration <=", value, "duration");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andDurationIn(List<Integer> values) {
            this.addCriterion("Duration in", values, "duration");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andDurationNotIn(List<Integer> values) {
            this.addCriterion("Duration not in", values, "duration");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andDurationBetween(Integer value1, Integer value2) {
            this.addCriterion("Duration between", value1, value2, "duration");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andDurationNotBetween(Integer value1, Integer value2) {
            this.addCriterion("Duration not between", value1, value2, "duration");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andPeerCarrierIsNull() {
            this.addCriterion("Peer_Carrier is null");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andPeerCarrierIsNotNull() {
            this.addCriterion("Peer_Carrier is not null");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andPeerCarrierEqualTo(String value) {
            this.addCriterion("Peer_Carrier =", value, "peerCarrier");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andPeerCarrierNotEqualTo(String value) {
            this.addCriterion("Peer_Carrier <>", value, "peerCarrier");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andPeerCarrierGreaterThan(String value) {
            this.addCriterion("Peer_Carrier >", value, "peerCarrier");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andPeerCarrierGreaterThanOrEqualTo(String value) {
            this.addCriterion("Peer_Carrier >=", value, "peerCarrier");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andPeerCarrierLessThan(String value) {
            this.addCriterion("Peer_Carrier <", value, "peerCarrier");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andPeerCarrierLessThanOrEqualTo(String value) {
            this.addCriterion("Peer_Carrier <=", value, "peerCarrier");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andPeerCarrierLike(String value) {
            this.addCriterion("Peer_Carrier like", value, "peerCarrier");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andPeerCarrierNotLike(String value) {
            this.addCriterion("Peer_Carrier not like", value, "peerCarrier");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andPeerCarrierIn(List<String> values) {
            this.addCriterion("Peer_Carrier in", values, "peerCarrier");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andPeerCarrierNotIn(List<String> values) {
            this.addCriterion("Peer_Carrier not in", values, "peerCarrier");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andPeerCarrierBetween(String value1, String value2) {
            this.addCriterion("Peer_Carrier between", value1, value2, "peerCarrier");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andPeerCarrierNotBetween(String value1, String value2) {
            this.addCriterion("Peer_Carrier not between", value1, value2, "peerCarrier");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andDialTypeIsNull() {
            this.addCriterion("Dial_Type is null");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andDialTypeIsNotNull() {
            this.addCriterion("Dial_Type is not null");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andDialTypeEqualTo(String value) {
            this.addCriterion("Dial_Type =", value, "dialType");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andDialTypeNotEqualTo(String value) {
            this.addCriterion("Dial_Type <>", value, "dialType");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andDialTypeGreaterThan(String value) {
            this.addCriterion("Dial_Type >", value, "dialType");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andDialTypeGreaterThanOrEqualTo(String value) {
            this.addCriterion("Dial_Type >=", value, "dialType");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andDialTypeLessThan(String value) {
            this.addCriterion("Dial_Type <", value, "dialType");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andDialTypeLessThanOrEqualTo(String value) {
            this.addCriterion("Dial_Type <=", value, "dialType");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andDialTypeLike(String value) {
            this.addCriterion("Dial_Type like", value, "dialType");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andDialTypeNotLike(String value) {
            this.addCriterion("Dial_Type not like", value, "dialType");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andDialTypeIn(List<String> values) {
            this.addCriterion("Dial_Type in", values, "dialType");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andDialTypeNotIn(List<String> values) {
            this.addCriterion("Dial_Type not in", values, "dialType");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andDialTypeBetween(String value1, String value2) {
            this.addCriterion("Dial_Type between", value1, value2, "dialType");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andDialTypeNotBetween(String value1, String value2) {
            this.addCriterion("Dial_Type not between", value1, value2, "dialType");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andFeeIsNull() {
            this.addCriterion("Fee is null");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andFeeIsNotNull() {
            this.addCriterion("Fee is not null");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andFeeEqualTo(Integer value) {
            this.addCriterion("Fee =", value, "fee");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andFeeNotEqualTo(Integer value) {
            this.addCriterion("Fee <>", value, "fee");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andFeeGreaterThan(Integer value) {
            this.addCriterion("Fee >", value, "fee");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andFeeGreaterThanOrEqualTo(Integer value) {
            this.addCriterion("Fee >=", value, "fee");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andFeeLessThan(Integer value) {
            this.addCriterion("Fee <", value, "fee");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andFeeLessThanOrEqualTo(Integer value) {
            this.addCriterion("Fee <=", value, "fee");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andFeeIn(List<Integer> values) {
            this.addCriterion("Fee in", values, "fee");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andFeeNotIn(List<Integer> values) {
            this.addCriterion("Fee not in", values, "fee");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andFeeBetween(Integer value1, Integer value2) {
            this.addCriterion("Fee between", value1, value2, "fee");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andFeeNotBetween(Integer value1, Integer value2) {
            this.addCriterion("Fee not between", value1, value2, "fee");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andIsBlackIsNull() {
            this.addCriterion("Is_Black is null");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andIsBlackIsNotNull() {
            this.addCriterion("Is_Black is not null");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andIsBlackEqualTo(Boolean value) {
            this.addCriterion("Is_Black =", value, "isBlack");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andIsBlackNotEqualTo(Boolean value) {
            this.addCriterion("Is_Black <>", value, "isBlack");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andIsBlackGreaterThan(Boolean value) {
            this.addCriterion("Is_Black >", value, "isBlack");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andIsBlackGreaterThanOrEqualTo(Boolean value) {
            this.addCriterion("Is_Black >=", value, "isBlack");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andIsBlackLessThan(Boolean value) {
            this.addCriterion("Is_Black <", value, "isBlack");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andIsBlackLessThanOrEqualTo(Boolean value) {
            this.addCriterion("Is_Black <=", value, "isBlack");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andIsBlackIn(List<Boolean> values) {
            this.addCriterion("Is_Black in", values, "isBlack");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andIsBlackNotIn(List<Boolean> values) {
            this.addCriterion("Is_Black not in", values, "isBlack");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andIsBlackBetween(Boolean value1, Boolean value2) {
            this.addCriterion("Is_Black between", value1, value2, "isBlack");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andIsBlackNotBetween(Boolean value1, Boolean value2) {
            this.addCriterion("Is_Black not between", value1, value2, "isBlack");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andIsCloudblackIsNull() {
            this.addCriterion("Is_CloudBlack is null");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andIsCloudblackIsNotNull() {
            this.addCriterion("Is_CloudBlack is not null");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andIsCloudblackEqualTo(Boolean value) {
            this.addCriterion("Is_CloudBlack =", value, "isCloudblack");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andIsCloudblackNotEqualTo(Boolean value) {
            this.addCriterion("Is_CloudBlack <>", value, "isCloudblack");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andIsCloudblackGreaterThan(Boolean value) {
            this.addCriterion("Is_CloudBlack >", value, "isCloudblack");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andIsCloudblackGreaterThanOrEqualTo(Boolean value) {
            this.addCriterion("Is_CloudBlack >=", value, "isCloudblack");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andIsCloudblackLessThan(Boolean value) {
            this.addCriterion("Is_CloudBlack <", value, "isCloudblack");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andIsCloudblackLessThanOrEqualTo(Boolean value) {
            this.addCriterion("Is_CloudBlack <=", value, "isCloudblack");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andIsCloudblackIn(List<Boolean> values) {
            this.addCriterion("Is_CloudBlack in", values, "isCloudblack");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andIsCloudblackNotIn(List<Boolean> values) {
            this.addCriterion("Is_CloudBlack not in", values, "isCloudblack");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andIsCloudblackBetween(Boolean value1, Boolean value2) {
            this.addCriterion("Is_CloudBlack between", value1, value2, "isCloudblack");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andIsCloudblackNotBetween(Boolean value1, Boolean value2) {
            this.addCriterion("Is_CloudBlack not between", value1, value2, "isCloudblack");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andIsP2pplatIsNull() {
            this.addCriterion("Is_P2PPlat is null");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andIsP2pplatIsNotNull() {
            this.addCriterion("Is_P2PPlat is not null");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andIsP2pplatEqualTo(Boolean value) {
            this.addCriterion("Is_P2PPlat =", value, "isP2pplat");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andIsP2pplatNotEqualTo(Boolean value) {
            this.addCriterion("Is_P2PPlat <>", value, "isP2pplat");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andIsP2pplatGreaterThan(Boolean value) {
            this.addCriterion("Is_P2PPlat >", value, "isP2pplat");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andIsP2pplatGreaterThanOrEqualTo(Boolean value) {
            this.addCriterion("Is_P2PPlat >=", value, "isP2pplat");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andIsP2pplatLessThan(Boolean value) {
            this.addCriterion("Is_P2PPlat <", value, "isP2pplat");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andIsP2pplatLessThanOrEqualTo(Boolean value) {
            this.addCriterion("Is_P2PPlat <=", value, "isP2pplat");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andIsP2pplatIn(List<Boolean> values) {
            this.addCriterion("Is_P2PPlat in", values, "isP2pplat");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andIsP2pplatNotIn(List<Boolean> values) {
            this.addCriterion("Is_P2PPlat not in", values, "isP2pplat");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andIsP2pplatBetween(Boolean value1, Boolean value2) {
            this.addCriterion("Is_P2PPlat between", value1, value2, "isP2pplat");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andIsP2pplatNotBetween(Boolean value1, Boolean value2) {
            this.addCriterion("Is_P2PPlat not between", value1, value2, "isP2pplat");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andCalledProvIsNull() {
            this.addCriterion("called_prov is null");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andCalledProvIsNotNull() {
            this.addCriterion("called_prov is not null");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andCalledProvEqualTo(String value) {
            this.addCriterion("called_prov =", value, "calledProv");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andCalledProvNotEqualTo(String value) {
            this.addCriterion("called_prov <>", value, "calledProv");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andCalledProvGreaterThan(String value) {
            this.addCriterion("called_prov >", value, "calledProv");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andCalledProvGreaterThanOrEqualTo(String value) {
            this.addCriterion("called_prov >=", value, "calledProv");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andCalledProvLessThan(String value) {
            this.addCriterion("called_prov <", value, "calledProv");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andCalledProvLessThanOrEqualTo(String value) {
            this.addCriterion("called_prov <=", value, "calledProv");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andCalledProvLike(String value) {
            this.addCriterion("called_prov like", value, "calledProv");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andCalledProvNotLike(String value) {
            this.addCriterion("called_prov not like", value, "calledProv");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andCalledProvIn(List<String> values) {
            this.addCriterion("called_prov in", values, "calledProv");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andCalledProvNotIn(List<String> values) {
            this.addCriterion("called_prov not in", values, "calledProv");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andCalledProvBetween(String value1, String value2) {
            this.addCriterion("called_prov between", value1, value2, "calledProv");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andCalledProvNotBetween(String value1, String value2) {
            this.addCriterion("called_prov not between", value1, value2, "calledProv");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andCalledCityIsNull() {
            this.addCriterion("called_city is null");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andCalledCityIsNotNull() {
            this.addCriterion("called_city is not null");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andCalledCityEqualTo(String value) {
            this.addCriterion("called_city =", value, "calledCity");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andCalledCityNotEqualTo(String value) {
            this.addCriterion("called_city <>", value, "calledCity");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andCalledCityGreaterThan(String value) {
            this.addCriterion("called_city >", value, "calledCity");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andCalledCityGreaterThanOrEqualTo(String value) {
            this.addCriterion("called_city >=", value, "calledCity");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andCalledCityLessThan(String value) {
            this.addCriterion("called_city <", value, "calledCity");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andCalledCityLessThanOrEqualTo(String value) {
            this.addCriterion("called_city <=", value, "calledCity");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andCalledCityLike(String value) {
            this.addCriterion("called_city like", value, "calledCity");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andCalledCityNotLike(String value) {
            this.addCriterion("called_city not like", value, "calledCity");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andCalledCityIn(List<String> values) {
            this.addCriterion("called_city in", values, "calledCity");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andCalledCityNotIn(List<String> values) {
            this.addCriterion("called_city not in", values, "calledCity");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andCalledCityBetween(String value1, String value2) {
            this.addCriterion("called_city between", value1, value2, "calledCity");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andCalledCityNotBetween(String value1, String value2) {
            this.addCriterion("called_city not between", value1, value2, "calledCity");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andTaskIdIsNull() {
            this.addCriterion("TASK_ID is null");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andTaskIdIsNotNull() {
            this.addCriterion("TASK_ID is not null");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andTaskIdEqualTo(String value) {
            this.addCriterion("TASK_ID =", value, "taskId");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andTaskIdNotEqualTo(String value) {
            this.addCriterion("TASK_ID <>", value, "taskId");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andTaskIdGreaterThan(String value) {
            this.addCriterion("TASK_ID >", value, "taskId");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andTaskIdGreaterThanOrEqualTo(String value) {
            this.addCriterion("TASK_ID >=", value, "taskId");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andTaskIdLessThan(String value) {
            this.addCriterion("TASK_ID <", value, "taskId");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andTaskIdLessThanOrEqualTo(String value) {
            this.addCriterion("TASK_ID <=", value, "taskId");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andTaskIdLike(String value) {
            this.addCriterion("TASK_ID like", value, "taskId");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andTaskIdNotLike(String value) {
            this.addCriterion("TASK_ID not like", value, "taskId");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andTaskIdIn(List<String> values) {
            this.addCriterion("TASK_ID in", values, "taskId");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andTaskIdNotIn(List<String> values) {
            this.addCriterion("TASK_ID not in", values, "taskId");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andTaskIdBetween(String value1, String value2) {
            this.addCriterion("TASK_ID between", value1, value2, "taskId");
            return (DSTelVoiceCallExample.Criteria)this;
        }

        public DSTelVoiceCallExample.Criteria andTaskIdNotBetween(String value1, String value2) {
            this.addCriterion("TASK_ID not between", value1, value2, "taskId");
            return (DSTelVoiceCallExample.Criteria)this;
        }
    }
}