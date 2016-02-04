package controller;

import java.util.List;
import java.util.Map;

import org.apache.struts2.interceptor.RequestAware;

import com.opensymphony.xwork2.Action;
import com.opensymphony.xwork2.ActionSupport;

import model.ProductBean;
import model.ProductService;

public class ProductAction extends ActionSupport implements RequestAware{

	
	private ProductBean bean = new ProductBean();
	private String prodaction;
	private ProductService productservice = new ProductService();
	
	public ProductBean getBean() {
		return bean;
	}
	public void setBean(ProductBean bean) {
		this.bean = bean;
	}
	
	public String getProdaction() {
		return prodaction;
	}
	public void setProdaction(String prodaction) {
		this.prodaction = prodaction;
	}
	
	
	
	private Map<String, Object> request;
	@Override
	public void setRequest(Map<String, Object> request) {
		this.request=request;
		
	}

	
	@Override
	public void validate() {
		
		
		if("Insert".equals(prodaction) || "Update".equals(prodaction) || "Delete".equals(prodaction)) {
			if(bean.getId()==0) {
				this.addFieldError("bean.id", "bean.id.required");
			}
		}
	}
	
	
	
	
	
	
	@Override
	public String execute() throws Exception {

		if("Select".equals(prodaction)){
			List<ProductBean> result = productservice.select(bean);
			
			request.put("select", result);
			
//			request.getRequestDispatcher("/pages/display.jsp").forward(request, response);
			
			return Action.SUCCESS;
		}else if("Insert".equals(prodaction)){
			ProductBean result = productservice.insert(bean);
			
			if(result==null){
				this.addFieldError("action",this.getText("bean.insert.failed"));				
			}else{
				request.put("insert", result);
			}
			
			return Action.INPUT;
			
//			request.getRequestDispatcher("/pages/display.jsp").forward(request, response);	
//			this.addFieldError("Select",this.getText("Select.error"));
			
		}else if("Update".equals(prodaction)){
			ProductBean result = productservice.update(bean);
			
			if(result==null){
				this.addFieldError("action",this.getText("bean.update.failed"));				
			}else{
				request.put("update", result);
			}
			return Action.INPUT;
			
			
//			request.getRequestDispatcher("/pages/display.jsp").forward(request, response);
		
		}else if("Delete".equals(prodaction)){
			boolean result = productservice.delete(bean);		
				
				this.addFieldError("action",this.getText("bean.delete.failed"));				
				request.put("delete", result);
			
			
			return Action.INPUT;
			
//			request.getRequestDispatcher("/pages/display.jsp").forward(request, response);
		
		}else {
			this.addFieldError("action",this.getText("bean.unknowed"));
			return Action.INPUT;
		}
	}

}
