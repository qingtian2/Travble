package com.travbao.news.travbao.bean;

import java.util.List;

/**
 * Created by lijinbo on 18/1/1.
 */

public class NewsBean {


    /**
     * data : [{"newsFrom":"","newsHeadline":" 詹皇成最年轻总得分达3万分的NBA球员","newsHeadlineUrl":"http://www.zaobao.com/realtime/world/story20180124-829701","newsImgurl1":"http://www.zaobao.com/sites/default/files/styles/article_medium_crop/public/images/201801/20180124/2018-01-24t022712z_84454865_nocid_rtrmadp_3_nba-cleveland-cavaliers-at-san-antonio-spurs.jpg?itok=irlEz8q4×tamp=1516766573 1x","newsImgurl2":"","newsImgurl3":"","newsNcontent":"","newsNo":"20180124010","newsNtime":"2018-01-24 12:02 ","newsSummary":"","newscountry":"","newstype":"快讯"},{"newsFrom":"","newsHeadline":" 特朗普将在世界经济论坛为美招商引资","newsHeadlineUrl":"http://www.zaobao.com/realtime/world/story20180124-829696","newsImgurl1":"http://www.zaobao.com/sites/default/files/styles/article_medium_crop/public/images/201801/20180124/naipc201801241139171.jpg?itok=ShjRzrWa×tamp=1516765338 1x","newsImgurl2":"","newsImgurl3":"","newsNcontent":"","newsNo":"20180124011","newsNtime":"2018-01-24 11:42 ","newsSummary":"","newscountry":"","newstype":"快讯"},{"newsFrom":"","newsHeadline":" 美军直升机今年第三次在冲绳县内迫降","newsHeadlineUrl":"http://www.zaobao.com/realtime/world/story20180124-829692","newsImgurl1":"","newsImgurl2":"","newsImgurl3":"","newsNcontent":"","newsNo":"20180124012","newsNtime":"2018-01-24 11:30 ","newsSummary":"","newscountry":"","newstype":"快讯"},{"newsFrom":"","newsHeadline":" 俄印将签署直升机供货合同","newsHeadlineUrl":"http://www.zaobao.com/realtime/world/story20180124-829688","newsImgurl1":"http://www.zaobao.com/sites/default/files/styles/article_medium_crop/public/images/201801/20180124/naipc201801240909471.jpg?itok=cohkMmTo×tamp=1516763008 1x","newsImgurl2":"","newsImgurl3":"","newsNcontent":"","newsNo":"20180124013","newsNtime":"2018-01-24 11:03 ","newsSummary":"","newscountry":"","newstype":"快讯"},{"newsFrom":"","newsHeadline":" 巴黎塞纳河水高涨 游船停驶步道封闭","newsHeadlineUrl":"http://www.zaobao.com/realtime/world/story20180124-829687","newsImgurl1":"http://www.zaobao.com/sites/default/files/styles/article_medium_crop/public/images/201801/20180124/naipc201801240932341.jpg?itok=wP-4JtY7×tamp=1516762856 1x","newsImgurl2":"","newsImgurl3":"","newsNcontent":"","newsNo":"20180124014","newsNtime":"2018-01-24 11:00 ","newsSummary":"","newscountry":"","newstype":"快讯"},{"newsFrom":"","newsHeadline":" 韩国入冬最强寒潮 首尔零下15.9度","newsHeadlineUrl":"http://www.zaobao.com/realtime/world/story20180124-829686","newsImgurl1":"","newsImgurl2":"","newsImgurl3":"","newsNcontent":"","newsNo":"20180124015","newsNtime":"2018-01-24 10:41 ","newsSummary":"","newscountry":"","newstype":"快讯"},{"newsFrom":"","newsHeadline":" 美奇幻女作家勒瑰恩逝世 享年88岁","newsHeadlineUrl":"http://www.zaobao.com/realtime/world/story20180124-829684","newsImgurl1":"http://www.zaobao.com/sites/default/files/styles/article_medium_crop/public/images/201801/20180124/naipc201801241028101.jpg?itok=o8rNOOF1×tamp=1516760908 1x","newsImgurl2":"","newsImgurl3":"","newsNcontent":"","newsNo":"20180124016","newsNtime":"2018-01-24 10:28 ","newsSummary":"","newscountry":"","newstype":"快讯"},{"newsFrom":"","newsHeadline":" 利比亚连环汽车爆炸案 至少33人死亡","newsHeadlineUrl":"http://www.zaobao.com/realtime/world/story20180124-829676","newsImgurl1":"","newsImgurl2":"","newsImgurl3":"","newsNcontent":"","newsNo":"20180124017","newsNtime":"2018-01-24 10:01 ","newsSummary":"","newscountry":"","newstype":"快讯"},{"newsFrom":"","newsHeadline":" 美中情局长：朝鲜核导项目也是为了恐吓","newsHeadlineUrl":"http://www.zaobao.com/realtime/world/story20180124-829675","newsImgurl1":"http://www.zaobao.com/sites/default/files/styles/article_medium_crop/public/images/201801/20180124/naipc201801240942121.jpg?itok=l5E939-4×tamp=1516758171 1x","newsImgurl2":"","newsImgurl3":"","newsNcontent":"","newsNo":"20180124018","newsNtime":"2018-01-24 09:42 ","newsSummary":"","newscountry":"","newstype":"快讯"},{"newsFrom":"","newsHeadline":" 意大利派直升机疏散雪崩危险区酒店旅客","newsHeadlineUrl":"http://www.zaobao.com/realtime/world/story20180124-829671","newsImgurl1":"http://www.zaobao.com/sites/default/files/styles/article_medium_crop/public/images/201801/20180124/naipc201801240924521.jpg?itok=w2M8l5P8×tamp=1516757232 1x","newsImgurl2":"","newsImgurl3":"","newsNcontent":"","newsNo":"20180124019","newsNtime":"2018-01-24 09:27 ","newsSummary":"","newscountry":"","newstype":"快讯"}]
     * limit : 10
     * message : 查询成功
     * pageFlag : true
     * start : 10
     * success : true
     * total : 671
     */

    private int limit;
    private String message;
    private boolean pageFlag;
    private int start;
    private boolean success;
    private int total;
    private List<DataBean> data;

    public int getLimit() {
        return limit;
    }

    public void setLimit(int limit) {
        this.limit = limit;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isPageFlag() {
        return pageFlag;
    }

    public void setPageFlag(boolean pageFlag) {
        this.pageFlag = pageFlag;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * newsFrom :
         * newsHeadline :  詹皇成最年轻总得分达3万分的NBA球员
         * newsHeadlineUrl : http://www.zaobao.com/realtime/world/story20180124-829701
         * newsImgurl1 : http://www.zaobao.com/sites/default/files/styles/article_medium_crop/public/images/201801/20180124/2018-01-24t022712z_84454865_nocid_rtrmadp_3_nba-cleveland-cavaliers-at-san-antonio-spurs.jpg?itok=irlEz8q4×tamp=1516766573 1x
         * newsImgurl2 :
         * newsImgurl3 :
         * newsNcontent :
         * newsNo : 20180124010
         * newsNtime : 2018-01-24 12:02
         * newsSummary :
         * newscountry :
         * newstype : 快讯
         */

        private String newsFrom;
        private String newsHeadline;
        private String newsHeadlineUrl;
        private String newsImgurl1;
        private String newsImgurl2;
        private String newsImgurl3;
        private String newsNcontent;
        private String newsNo;
        private String newsNtime;
        private String newsSummary;
        private String newscountry;
        private String newstype;

        public String getNewsFrom() {
            return newsFrom;
        }

        public void setNewsFrom(String newsFrom) {
            this.newsFrom = newsFrom;
        }

        public String getNewsHeadline() {
            return newsHeadline;
        }

        public void setNewsHeadline(String newsHeadline) {
            this.newsHeadline = newsHeadline;
        }

        public String getNewsHeadlineUrl() {
            return newsHeadlineUrl;
        }

        public void setNewsHeadlineUrl(String newsHeadlineUrl) {
            this.newsHeadlineUrl = newsHeadlineUrl;
        }

        public String getNewsImgurl1() {
            return newsImgurl1;
        }

        public void setNewsImgurl1(String newsImgurl1) {
            this.newsImgurl1 = newsImgurl1;
        }

        public String getNewsImgurl2() {
            return newsImgurl2;
        }

        public void setNewsImgurl2(String newsImgurl2) {
            this.newsImgurl2 = newsImgurl2;
        }

        public String getNewsImgurl3() {
            return newsImgurl3;
        }

        public void setNewsImgurl3(String newsImgurl3) {
            this.newsImgurl3 = newsImgurl3;
        }

        public String getNewsNcontent() {
            return newsNcontent;
        }

        public void setNewsNcontent(String newsNcontent) {
            this.newsNcontent = newsNcontent;
        }

        public String getNewsNo() {
            return newsNo;
        }

        public void setNewsNo(String newsNo) {
            this.newsNo = newsNo;
        }

        public String getNewsNtime() {
            return newsNtime;
        }

        public void setNewsNtime(String newsNtime) {
            this.newsNtime = newsNtime;
        }

        public String getNewsSummary() {
            return newsSummary;
        }

        public void setNewsSummary(String newsSummary) {
            this.newsSummary = newsSummary;
        }

        public String getNewscountry() {
            return newscountry;
        }

        public void setNewscountry(String newscountry) {
            this.newscountry = newscountry;
        }

        public String getNewstype() {
            return newstype;
        }

        public void setNewstype(String newstype) {
            this.newstype = newstype;
        }
    }
}
