/**
 * 
 */
package za.co.discovery.assignment.domain.web.vo;

/**
 * @author Harika
 *
 */
public class ResponseVO<T> {
	private T results;

	public ResponseVO(T results) {
		this.results = results;
	}

	public T getResults() {
		return this.results;
	}
}
