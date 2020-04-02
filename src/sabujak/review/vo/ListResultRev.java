package sabujak.review.vo;

import java.util.List;

import sabujak.domain.Review;


public class ListResultRev {
	private int page;
	private long totalCount;
	private int pageSize;
	private List<Review> list;
	private long totalPageCount;
	
	public ListResultRev() {
	}
	public ListResultRev(int page, long totalCount, int pageSize, List<Review> list) {
		this.page = page;
		this.totalCount = totalCount;
		this.pageSize = pageSize;
		this.list = list;
		this.totalPageCount = calTotalPageCount();
	}
	
	public long calTotalPageCount(){
		long tpc = totalCount/pageSize;
		if(totalCount%pageSize != 0) tpc++;
		
		return tpc;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public long getTotalCount() {
		return totalCount;
	}
	public void setTotalCount(long totalCount) {
		this.totalCount = totalCount;
	}
	public int getPageSize() {
		return pageSize;
	}
	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}
	public List<Review> getList() {
		return list;
	}
	public void setList(List<Review> list) {
		this.list = list;
	}
	public long getTotalPageCount() {
		return totalPageCount;
	}
	public void setTotalPageCount(long totalPageCount) {
		this.totalPageCount = totalPageCount;
	}
	
}
