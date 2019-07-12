package domain;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PageBean<T> {

	private int currentPage;
	private int currentCount;
	private int totalCount;
	private int totalPage;
	private List<T> list;
}
