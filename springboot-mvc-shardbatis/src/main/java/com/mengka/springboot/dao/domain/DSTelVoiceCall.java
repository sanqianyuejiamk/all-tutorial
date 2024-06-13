package com.mengka.springboot.dao.domain;

import java.io.Serializable;
import java.util.Date;

/**
 * @author huangyy
 * @description
 * @date 2017/04/30.
 */
public class DSTelVoiceCall implements Serializable {
    private String tenantId;
    private String userId;
    private String mobile;
    private Date time;
    private String peerNumber;
    private String location;
    private String locationType;
    private Integer duration;
    private String peerCarrier;
    private String dialType;
    private Integer fee;
    private Boolean isBlack;
    private Boolean isCloudblack;
    private Boolean isP2pplat;
    private String calledProv;
    private String calledCity;
    private String taskId;
    private static final long serialVersionUID = 1L;

    public DSTelVoiceCall(String tenantId, String userId, String mobile, Date time, String peerNumber, String location, String locationType, Integer duration, String peerCarrier, String dialType, Integer fee, Boolean isBlack, Boolean isCloudblack, Boolean isP2pplat, String calledProv, String calledCity, String taskId) {
        this.tenantId = tenantId;
        this.userId = userId;
        this.mobile = mobile;
        this.time = time;
        this.peerNumber = peerNumber;
        this.location = location;
        this.locationType = locationType;
        this.duration = duration;
        this.peerCarrier = peerCarrier;
        this.dialType = dialType;
        this.fee = fee;
        this.isBlack = isBlack;
        this.isCloudblack = isCloudblack;
        this.isP2pplat = isP2pplat;
        this.calledProv = calledProv;
        this.calledCity = calledCity;
        this.taskId = taskId;
    }

    public DSTelVoiceCall() {
    }

    public String getTenantId() {
        return this.tenantId;
    }

    public void setTenantId(String tenantId) {
        this.tenantId = tenantId == null?null:tenantId.trim();
    }

    public String getUserId() {
        return this.userId;
    }

    public void setUserId(String userId) {
        this.userId = userId == null?null:userId.trim();
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile == null?null:mobile.trim();
    }

    public Date getTime() {
        return this.time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getPeerNumber() {
        return this.peerNumber;
    }

    public void setPeerNumber(String peerNumber) {
        this.peerNumber = peerNumber == null?null:peerNumber.trim();
    }

    public String getLocation() {
        return this.location;
    }

    public void setLocation(String location) {
        this.location = location == null?null:location.trim();
    }

    public String getLocationType() {
        return this.locationType;
    }

    public void setLocationType(String locationType) {
        this.locationType = locationType == null?null:locationType.trim();
    }

    public Integer getDuration() {
        return this.duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public String getPeerCarrier() {
        return this.peerCarrier;
    }

    public void setPeerCarrier(String peerCarrier) {
        this.peerCarrier = peerCarrier == null?null:peerCarrier.trim();
    }

    public String getDialType() {
        return this.dialType;
    }

    public void setDialType(String dialType) {
        this.dialType = dialType == null?null:dialType.trim();
    }

    public Integer getFee() {
        return this.fee;
    }

    public void setFee(Integer fee) {
        this.fee = fee;
    }

    public Boolean getIsBlack() {
        return this.isBlack;
    }

    public void setIsBlack(Boolean isBlack) {
        this.isBlack = isBlack;
    }

    public Boolean getIsCloudblack() {
        return this.isCloudblack;
    }

    public void setIsCloudblack(Boolean isCloudblack) {
        this.isCloudblack = isCloudblack;
    }

    public Boolean getIsP2pplat() {
        return this.isP2pplat;
    }

    public void setIsP2pplat(Boolean isP2pplat) {
        this.isP2pplat = isP2pplat;
    }

    public String getCalledProv() {
        return this.calledProv;
    }

    public void setCalledProv(String calledProv) {
        this.calledProv = calledProv == null?null:calledProv.trim();
    }

    public String getCalledCity() {
        return this.calledCity;
    }

    public void setCalledCity(String calledCity) {
        this.calledCity = calledCity == null?null:calledCity.trim();
    }

    public String getTaskId() {
        return this.taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId == null?null:taskId.trim();
    }

    public boolean equals(Object that) {
        if(this == that) {
            return true;
        } else if(that == null) {
            return false;
        } else if(this.getClass() != that.getClass()) {
            return false;
        } else {
            DSTelVoiceCall other = (DSTelVoiceCall)that;
            if(this.getTenantId() == null) {
                if(other.getTenantId() != null) {
                    return false;
                }
            } else if(!this.getTenantId().equals(other.getTenantId())) {
                return false;
            }

            if(this.getUserId() == null) {
                if(other.getUserId() != null) {
                    return false;
                }
            } else if(!this.getUserId().equals(other.getUserId())) {
                return false;
            }

            if(this.getMobile() == null) {
                if(other.getMobile() != null) {
                    return false;
                }
            } else if(!this.getMobile().equals(other.getMobile())) {
                return false;
            }

            if(this.getTime() == null) {
                if(other.getTime() != null) {
                    return false;
                }
            } else if(!this.getTime().equals(other.getTime())) {
                return false;
            }

            if(this.getPeerNumber() == null) {
                if(other.getPeerNumber() != null) {
                    return false;
                }
            } else if(!this.getPeerNumber().equals(other.getPeerNumber())) {
                return false;
            }

            if(this.getLocation() == null) {
                if(other.getLocation() != null) {
                    return false;
                }
            } else if(!this.getLocation().equals(other.getLocation())) {
                return false;
            }

            if(this.getLocationType() == null) {
                if(other.getLocationType() != null) {
                    return false;
                }
            } else if(!this.getLocationType().equals(other.getLocationType())) {
                return false;
            }

            if(this.getDuration() == null) {
                if(other.getDuration() != null) {
                    return false;
                }
            } else if(!this.getDuration().equals(other.getDuration())) {
                return false;
            }

            if(this.getPeerCarrier() == null) {
                if(other.getPeerCarrier() != null) {
                    return false;
                }
            } else if(!this.getPeerCarrier().equals(other.getPeerCarrier())) {
                return false;
            }

            if(this.getDialType() == null) {
                if(other.getDialType() != null) {
                    return false;
                }
            } else if(!this.getDialType().equals(other.getDialType())) {
                return false;
            }

            if(this.getFee() == null) {
                if(other.getFee() != null) {
                    return false;
                }
            } else if(!this.getFee().equals(other.getFee())) {
                return false;
            }

            if(this.getIsBlack() == null) {
                if(other.getIsBlack() != null) {
                    return false;
                }
            } else if(!this.getIsBlack().equals(other.getIsBlack())) {
                return false;
            }

            if(this.getIsCloudblack() == null) {
                if(other.getIsCloudblack() != null) {
                    return false;
                }
            } else if(!this.getIsCloudblack().equals(other.getIsCloudblack())) {
                return false;
            }

            if(this.getIsP2pplat() == null) {
                if(other.getIsP2pplat() != null) {
                    return false;
                }
            } else if(!this.getIsP2pplat().equals(other.getIsP2pplat())) {
                return false;
            }

            if(this.getCalledProv() == null) {
                if(other.getCalledProv() != null) {
                    return false;
                }
            } else if(!this.getCalledProv().equals(other.getCalledProv())) {
                return false;
            }

            if(this.getCalledCity() == null) {
                if(other.getCalledCity() != null) {
                    return false;
                }
            } else if(!this.getCalledCity().equals(other.getCalledCity())) {
                return false;
            }

            if(this.getTaskId() == null) {
                if(other.getTaskId() == null) {
                    return true;
                }
            } else if(this.getTaskId().equals(other.getTaskId())) {
                return true;
            }

            return false;
        }
    }

    public int hashCode() {
        boolean prime = true;
        byte result = 1;
        int result1 = 31 * result + (this.getTenantId() == null?0:this.getTenantId().hashCode());
        result1 = 31 * result1 + (this.getUserId() == null?0:this.getUserId().hashCode());
        result1 = 31 * result1 + (this.getMobile() == null?0:this.getMobile().hashCode());
        result1 = 31 * result1 + (this.getTime() == null?0:this.getTime().hashCode());
        result1 = 31 * result1 + (this.getPeerNumber() == null?0:this.getPeerNumber().hashCode());
        result1 = 31 * result1 + (this.getLocation() == null?0:this.getLocation().hashCode());
        result1 = 31 * result1 + (this.getLocationType() == null?0:this.getLocationType().hashCode());
        result1 = 31 * result1 + (this.getDuration() == null?0:this.getDuration().hashCode());
        result1 = 31 * result1 + (this.getPeerCarrier() == null?0:this.getPeerCarrier().hashCode());
        result1 = 31 * result1 + (this.getDialType() == null?0:this.getDialType().hashCode());
        result1 = 31 * result1 + (this.getFee() == null?0:this.getFee().hashCode());
        result1 = 31 * result1 + (this.getIsBlack() == null?0:this.getIsBlack().hashCode());
        result1 = 31 * result1 + (this.getIsCloudblack() == null?0:this.getIsCloudblack().hashCode());
        result1 = 31 * result1 + (this.getIsP2pplat() == null?0:this.getIsP2pplat().hashCode());
        result1 = 31 * result1 + (this.getCalledProv() == null?0:this.getCalledProv().hashCode());
        result1 = 31 * result1 + (this.getCalledCity() == null?0:this.getCalledCity().hashCode());
        result1 = 31 * result1 + (this.getTaskId() == null?0:this.getTaskId().hashCode());
        return result1;
    }
}