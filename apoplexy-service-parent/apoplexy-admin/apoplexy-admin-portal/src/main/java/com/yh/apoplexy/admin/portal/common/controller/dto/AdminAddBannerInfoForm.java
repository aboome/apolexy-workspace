package com.yh.apoplexy.admin.portal.common.controller.dto;
/**
 * 新增新增主页推荐位表单
 * @author eyelake
 *
 */
public class AdminAddBannerInfoForm {
        private  String owner;
        
        private  String title;
        
        private String image;
        
        private String url;
        
        private String sort;

		public String getOwner() {
			return owner;
		}

		public void setOwner(String owner) {
			this.owner = owner;
		}

		public String getTitle() {
			return title;
		}

		public void setTitle(String title) {
			this.title = title;
		}


		public String getImage() {
			return image;
		}

		public void setImage(String image) {
			this.image = image;
		}

		public String getUrl() {
			return url;
		}

		public void setUrl(String url) {
			this.url = url;
		}

		public String getSort() {
			return sort;
		}

		public void setSort(String sort) {
			this.sort = sort;
		}
        
        
}
