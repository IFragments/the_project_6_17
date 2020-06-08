package com.cl.data;

import java.io.Serializable;
import java.util.List;

public class HomeMainBean {
    public List<Carousel> Carousel;
    public List<Live> live;

    public class Carousel implements Serializable {
        public String img;
        public String thumb;
        public String url;

        public String getImg() {
            return img;
        }

        public void setImg(String img) {
            this.img = img;
        }

        public String getThumb() {
            return thumb;
        }

        public void setThumb(String thumb) {
            this.thumb = thumb;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public class Live implements Serializable {
        public Live(String pActivityName) {
            activityName = pActivityName;
        }

        public String activityName;
        public String correlative_lessons;
        public String coverImgUrl;
        public String endTime;
        public String from_type;
        public String is_liveing;
        public String lesson_id;
        public String live_id;
        public String specialty_id;
        public String startTime;
        public String teacher_name;
        public String teacher_photo;
        public String teacher_uid;
        public String url;

        public String getActivityName() {
            return activityName;
        }

        public void setActivityName(String activityName) {
            this.activityName = activityName;
        }

        public String getCorrelative_lessons() {
            return correlative_lessons;
        }

        public void setCorrelative_lessons(String correlative_lessons) {
            this.correlative_lessons = correlative_lessons;
        }

        public String getCoverImgUrl() {
            return coverImgUrl;
        }

        public void setCoverImgUrl(String coverImgUrl) {
            this.coverImgUrl = coverImgUrl;
        }

        public String getEndTime() {
            return endTime;
        }

        public void setEndTime(String endTime) {
            this.endTime = endTime;
        }

        public String getFrom_type() {
            return from_type;
        }

        public void setFrom_type(String from_type) {
            this.from_type = from_type;
        }

        public String getIs_liveing() {
            return is_liveing;
        }

        public void setIs_liveing(String is_liveing) {
            this.is_liveing = is_liveing;
        }

        public String getLesson_id() {
            return lesson_id;
        }

        public void setLesson_id(String lesson_id) {
            this.lesson_id = lesson_id;
        }

        public String getLive_id() {
            return live_id;
        }

        public void setLive_id(String live_id) {
            this.live_id = live_id;
        }

        public String getSpecialty_id() {
            return specialty_id;
        }

        public void setSpecialty_id(String specialty_id) {
            this.specialty_id = specialty_id;
        }

        public String getStartTime() {
            return startTime;
        }

        public void setStartTime(String startTime) {
            this.startTime = startTime;
        }

        public String getTeacher_name() {
            return teacher_name;
        }

        public void setTeacher_name(String teacher_name) {
            this.teacher_name = teacher_name;
        }

        public String getTeacher_photo() {
            return teacher_photo;
        }

        public void setTeacher_photo(String teacher_photo) {
            this.teacher_photo = teacher_photo;
        }

        public String getTeacher_uid() {
            return teacher_uid;
        }

        public void setTeacher_uid(String teacher_uid) {
            this.teacher_uid = teacher_uid;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

//    /**
//     * errNo : 0
//     * result : {"Carousel":[{"url":"https://a.zhulong.com/poster/newjump/?plan_id=2913&prof=bim&placename_id=177&show_flag=1","img":"https://f.zhulong.com/poster/202006/05/30/184330e75nhawq3r7xgpcw_300_600_550_310.jpg?t=20190710","thumb":"https://f.zhulong.com/poster/202006/05/30/184330e75nhawq3r7xgpcw_300_600_550_310.jpg?t=20190710"},{"url":"https://a.zhulong.com/poster/newjump/?plan_id=4166&prof=bim&placename_id=178&show_flag=1","img":"https://f.zhulong.com/poster/202005/26/53/1009539y1kdqqbjpclwkwd_300_600_550_310.jpg?t=20190710","thumb":"https://f.zhulong.com/poster/202005/26/53/1009539y1kdqqbjpclwkwd_300_600_550_310.jpg?t=20190710"},{"url":"https://a.zhulong.com/poster/newjump/?plan_id=4160&prof=bim&placename_id=179&show_flag=1","img":"https://f.zhulong.com/poster/202001/16/59/175259k07528t7faidza4i_300_600_550_310.jpg?t=20190710","thumb":"https://f.zhulong.com/poster/202001/16/59/175259k07528t7faidza4i_300_600_550_310.jpg?t=20190710"},{"url":"https://a.zhulong.com/poster/newjump/?plan_id=4173&prof=bim&placename_id=180&show_flag=1","img":"https://f.zhulong.com/poster/202004/23/48/093548xi2b6ogxftr43puk_300_600_550_310.jpg?t=20190710","thumb":"https://f.zhulong.com/poster/202004/23/48/093548xi2b6ogxftr43puk_300_600_550_310.jpg?t=20190710"},{"url":"https://a.zhulong.com/poster/newjump/?plan_id=2501&prof=bim&placename_id=182&show_flag=1","img":"https://f.zhulong.com/poster/202001/07/49/113849xujupsagmeyjwij0_300_600_550_310.jpg?t=20190710","thumb":"https://f.zhulong.com/poster/202001/07/49/113849xujupsagmeyjwij0_300_600_550_310.jpg?t=20190710"}],"live":[{"live_id":"16972","teacher_name":"徐钢","coverImgUrl":"https://newoss.zhulong.com/edu/202005/18/16/0957168tdjlfitcvq9vxyx.jpg","activityName":"BIM考试二级结构直播答疑-第六次","correlative_lessons":"4971,9509","startTime":"1591530640","endTime":"1591535454","teacher_uid":"11442868","specialty_id":"21","is_liveing":0,"teacher_photo":"https://avatar.zhulong.com/avatar/011/44/28/68_avatar_big.jpg","lesson_id":"9509","from_type":1,"url":"http://edu.zhulong.com/lesson/9509-1.html"},{"live_id":"16580","teacher_name":"雷浩鹏","coverImgUrl":"https://newoss.zhulong.com/edu/202004/23/16/101116sguzegs8evz77nxc.jpg","activityName":"机电BIM工程师训练营第6-6次直播","correlative_lessons":"8266,9229,9206","startTime":"1591530641","endTime":"1591537800","teacher_uid":"11114735","specialty_id":"21","is_liveing":0,"teacher_photo":"https://avatar.zhulong.com/avatar/011/11/47/35_avatar_big.jpg","lesson_id":"9229","from_type":1,"url":"http://edu.zhulong.com/live/see_live?id=16580"},{"live_id":"16995","teacher_name":"熊圆圆","coverImgUrl":"https://newoss.zhulong.com/edu/202005/18/33/151633dmjmcti7sxv6pyer.png","activityName":"BIM等级考试楼梯栏杆答疑直播","correlative_lessons":"3079","startTime":"1591615209","endTime":"1591620028","teacher_uid":"14809648","specialty_id":"21","is_liveing":0,"teacher_photo":"https://avatar.zhulong.com/avatar/014/80/96/48_avatar_big.jpg?time=Array07","lesson_id":"3079","from_type":1,"url":"http://edu.zhulong.com/lesson/3079-1.html"},{"live_id":"16979","teacher_name":"董老师","coverImgUrl":"https://newoss.zhulong.com/edu/202005/18/24/1048248rnb9eps2juyjsdz.jpg","activityName":"BIM考试二级建筑直播答疑-六大参数","correlative_lessons":"4968,9507","startTime":"1591617001","endTime":"1591622114","teacher_uid":"12012111","specialty_id":"21","is_liveing":0,"teacher_photo":"https://avatar.zhulong.com/avatar/012/01/21/11_avatar_big.jpg","lesson_id":"9507","from_type":1,"url":"http://edu.zhulong.com/lesson/9507-1.html"},{"live_id":"16989","teacher_name":"曹林如","coverImgUrl":"http://newoss.zhulong.com/edu/202005/18/54/120754vcjgqbzhrk0vl3od.jpg","activityName":"筑龙一级屋顶讲解","correlative_lessons":"4606,9508,9507,9509","startTime":"1591703418","endTime":"1591708542","teacher_uid":"14414078","specialty_id":"21","is_liveing":0,"teacher_photo":"https://avatar.zhulong.com/avatar/014/41/40/78_avatar_big.jpg","lesson_id":"9509","from_type":1,"url":"http://edu.zhulong.com/lesson/9509-1.html"},{"live_id":"17176","teacher_name":"雷浩鹏","coverImgUrl":"https://newoss.zhulong.com/edu/202006/03/28/104928xebgmlxj5r2usav3.jpg","activityName":"设备二级设备风管讲解","correlative_lessons":"9508,4972,3550,9220","startTime":"1591703457","endTime":"1591708516","teacher_uid":"11114735","specialty_id":"21","is_liveing":0,"teacher_photo":"https://avatar.zhulong.com/avatar/011/11/47/35_avatar_big.jpg","lesson_id":"9508","from_type":1,"url":"http://edu.zhulong.com/lesson/9508-1.html"},{"live_id":"16943","teacher_name":"宋老师","coverImgUrl":"http://newoss.zhulong.com/edu/202005/15/35/150235klova5n5lxtrpqjw.png","activityName":"BIM等级考试体量答疑直播","correlative_lessons":"4606,3079","startTime":"1591787722","endTime":"1591793118","teacher_uid":"8585714","specialty_id":"21","is_liveing":0,"teacher_photo":"https://avatar.zhulong.com/avatar/008/58/57/14_avatar_big.jpg","lesson_id":"4606","from_type":1,"url":"http://edu.zhulong.com/lesson/4606-1.html"},{"live_id":"17135","teacher_name":"季纹旭","coverImgUrl":"http://newoss.zhulong.com/edu/202005/29/40/120540d2t5ct2zrqawvxgf.png","activityName":"BIM等级考试幕墙屋顶答疑直播","correlative_lessons":"4606","startTime":"1591960817","endTime":"1591965651","teacher_uid":"12679850","specialty_id":"21","is_liveing":0,"teacher_photo":"https://avatar.zhulong.com/avatar/012/67/98/50_avatar_big.jpg","lesson_id":"4606","from_type":1,"url":"http://edu.zhulong.com/lesson/4606-1.html"},{"live_id":"16996","teacher_name":"熊圆圆","coverImgUrl":"https://newoss.zhulong.com/edu/202005/18/16/152116eenvuah7syrmsqha.png","activityName":"BIM等级考试族答疑直播","correlative_lessons":"3079","startTime":"1591960828","endTime":"1591965657","teacher_uid":"14809648","specialty_id":"21","is_liveing":0,"teacher_photo":"https://avatar.zhulong.com/avatar/014/80/96/48_avatar_big.jpg?time=Array07","lesson_id":"3079","from_type":1,"url":"http://edu.zhulong.com/lesson/3079-1.html"},{"live_id":"17177","teacher_name":"雷浩鹏","coverImgUrl":"http://newoss.zhulong.com/edu/202006/03/33/105133ehctlh4wg4im2lbp.jpg","activityName":"筑龙二级设备专业卫浴讲解","correlative_lessons":"4972,9508,3550,9220","startTime":"1591962604","endTime":"1591967721","teacher_uid":"11114735","specialty_id":"21","is_liveing":0,"teacher_photo":"https://avatar.zhulong.com/avatar/011/11/47/35_avatar_big.jpg","lesson_id":"9508","from_type":1,"url":"http://edu.zhulong.com/lesson/9508-1.html"},{"live_id":"16990","teacher_name":"曹林如","coverImgUrl":"http://newoss.zhulong.com/edu/202005/18/39/120939qiboo41yvsaxl3tx.jpg","activityName":"筑龙一级楼梯讲解","correlative_lessons":"4606,9507,9508,9509","startTime":"1591962659","endTime":"1591967726","teacher_uid":"14414078","specialty_id":"21","is_liveing":0,"teacher_photo":"https://avatar.zhulong.com/avatar/014/41/40/78_avatar_big.jpg","lesson_id":"9509","from_type":1,"url":"http://edu.zhulong.com/lesson/9509-1.html"},{"live_id":"16581","teacher_name":"雷浩鹏","coverImgUrl":"https://newoss.zhulong.com/edu/202004/23/43/101243tdbnnf9irzz7ox6z.jpg","activityName":"机电BIM工程师训练营第6-7次直播","correlative_lessons":"8266,9229,9206","startTime":"1592135421","endTime":"1592140534","teacher_uid":"11114735","specialty_id":"21","is_liveing":0,"teacher_photo":"https://avatar.zhulong.com/avatar/011/11/47/35_avatar_big.jpg","lesson_id":"9229","from_type":1,"url":"http://edu.zhulong.com/lesson/9229-1.html"}]}
//     * upgrade : 0
//     * up_msg :
//     * exeTime : 0
//     */
//    private int errNo;
//
//    public void setErrNo(int errNo) {
//        this.errNo = errNo;
//    }
//
//    public int getErrNo() {
//        return errNo;
//    }
//
//    private ResultBean result;
//
//    public ResultBean getResult() {
//        return result;
//    }
//
//    public void setResult(ResultBean result) {
//        this.result = result;
//    }
//
//    public static class ResultBean {
//        private List<CarouselBean> Carousel;
//        private List<LiveBean> live;
//
//        public List<CarouselBean> getCarousel() {
//            return Carousel;
//        }
//
//        public void setCarousel(List<CarouselBean> Carousel) {
//            this.Carousel = Carousel;
//        }
//
//        public List<LiveBean> getLive() {
//            return live;
//        }
//
//        public void setLive(List<LiveBean> live) {
//            this.live = live;
//        }
//
//        public static class CarouselBean {
//            /**
//             * url : https://a.zhulong.com/poster/newjump/?plan_id=2913&prof=bim&placename_id=177&show_flag=1
//             * img : https://f.zhulong.com/poster/202006/05/30/184330e75nhawq3r7xgpcw_300_600_550_310.jpg?t=20190710
//             * thumb : https://f.zhulong.com/poster/202006/05/30/184330e75nhawq3r7xgpcw_300_600_550_310.jpg?t=20190710
//             */
//
//            private String url;
//            private String img;
//            private String thumb;
//
//            public String getUrl() {
//                return url;
//            }
//
//            public void setUrl(String url) {
//                this.url = url;
//            }
//
//            public String getImg() {
//                return img;
//            }
//
//            public void setImg(String img) {
//                this.img = img;
//            }
//
//            public String getThumb() {
//                return thumb;
//            }
//
//            public void setThumb(String thumb) {
//                this.thumb = thumb;
//            }
//        }
//
//        public static class LiveBean {
//            /**
//             * live_id : 16972
//             * teacher_name : 徐钢
//             * coverImgUrl : https://newoss.zhulong.com/edu/202005/18/16/0957168tdjlfitcvq9vxyx.jpg
//             * activityName : BIM考试二级结构直播答疑-第六次
//             * correlative_lessons : 4971,9509
//             * startTime : 1591530640
//             * endTime : 1591535454
//             * teacher_uid : 11442868
//             * specialty_id : 21
//             * is_liveing : 0
//             * teacher_photo : https://avatar.zhulong.com/avatar/011/44/28/68_avatar_big.jpg
//             * lesson_id : 9509
//             * from_type : 1
//             * url : http://edu.zhulong.com/lesson/9509-1.html
//             */
//
//            private String live_id;
//            private String teacher_name;
//            private String coverImgUrl;
//            private String activityName;
//            private String correlative_lessons;
//            private String startTime;
//            private String endTime;
//            private String teacher_uid;
//            private String specialty_id;
//            private int is_liveing;
//            private String teacher_photo;
//            private String lesson_id;
//            private int from_type;
//            private String url;
//
//            public String getLive_id() {
//                return live_id;
//            }
//
//            public void setLive_id(String live_id) {
//                this.live_id = live_id;
//            }
//
//            public String getTeacher_name() {
//                return teacher_name;
//            }
//
//            public void setTeacher_name(String teacher_name) {
//                this.teacher_name = teacher_name;
//            }
//
//            public String getCoverImgUrl() {
//                return coverImgUrl;
//            }
//
//            public void setCoverImgUrl(String coverImgUrl) {
//                this.coverImgUrl = coverImgUrl;
//            }
//
//            public String getActivityName() {
//                return activityName;
//            }
//
//            public void setActivityName(String activityName) {
//                this.activityName = activityName;
//            }
//
//            public String getCorrelative_lessons() {
//                return correlative_lessons;
//            }
//
//            public void setCorrelative_lessons(String correlative_lessons) {
//                this.correlative_lessons = correlative_lessons;
//            }
//
//            public String getStartTime() {
//                return startTime;
//            }
//
//            public void setStartTime(String startTime) {
//                this.startTime = startTime;
//            }
//
//            public String getEndTime() {
//                return endTime;
//            }
//
//            public void setEndTime(String endTime) {
//                this.endTime = endTime;
//            }
//
//            public String getTeacher_uid() {
//                return teacher_uid;
//            }
//
//            public void setTeacher_uid(String teacher_uid) {
//                this.teacher_uid = teacher_uid;
//            }
//
//            public String getSpecialty_id() {
//                return specialty_id;
//            }
//
//            public void setSpecialty_id(String specialty_id) {
//                this.specialty_id = specialty_id;
//            }
//
//            public int getIs_liveing() {
//                return is_liveing;
//            }
//
//            public void setIs_liveing(int is_liveing) {
//                this.is_liveing = is_liveing;
//            }
//
//            public String getTeacher_photo() {
//                return teacher_photo;
//            }
//
//            public void setTeacher_photo(String teacher_photo) {
//                this.teacher_photo = teacher_photo;
//            }
//
//            public String getLesson_id() {
//                return lesson_id;
//            }
//
//            public void setLesson_id(String lesson_id) {
//                this.lesson_id = lesson_id;
//            }
//
//            public int getFrom_type() {
//                return from_type;
//            }
//
//            public void setFrom_type(int from_type) {
//                this.from_type = from_type;
//            }
//
//            public String getUrl() {
//                return url;
//            }
//
//            public void setUrl(String url) {
//                this.url = url;
//            }
//        }
//    }
}
