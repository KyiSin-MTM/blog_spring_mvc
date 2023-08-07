package spring.blog.export;

import java.text.DateFormat;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsxView;

import spring.blog.persistence.entity.Post;

/**
 * <h2>PostExcelBuilder Class</h2>
 * <p>
 * Process for Displaying PostExcelBuilder
 * </p>
 * 
 * @author KyiSinShoonLaeLinn
 *
 */
public class PostExcelBuilder extends AbstractXlsxView {

    /**
     * <h2>DATE_FORMAT</h2>
     * <p>
     * DATE_FORMAT
     * </p>
     */
    private static final DateFormat DATE_FORMAT = DateFormat.getDateInstance(DateFormat.SHORT);

    /**
     * <h2>buildExcelDocument</h2>
     * <p>
     * 
     * </p>
     * 
     * @param model
     * @param workbook
     * @param request
     * @param response
     * @throws Exception
     */
    @SuppressWarnings("unchecked")
    @Override
    protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
            HttpServletResponse response) throws Exception {
        // TODO Auto-generated method stub
        response.setHeader("Content-Disposition", "attachment; filename=\"my-xls-file.xls\"");
        List<Post> posts = (List<Post>) model.get("posts");
        Sheet sheet = workbook.createSheet("Post List");

        // create header row
        Row header = sheet.createRow(0);
        header.createCell(0).setCellValue("ID");
        header.createCell(1).setCellValue("Title");
        header.createCell(2).setCellValue("Description");
        header.createCell(3).setCellValue("Created Date");

        int rowCount = 1;
        for (Post post : posts) {
            Row postRow = sheet.createRow(rowCount++);
            postRow.createCell(0).setCellValue(post.getId());
            postRow.createCell(1).setCellValue(post.getTitle());
            postRow.createCell(2).setCellValue(post.getDescription());
            postRow.createCell(3).setCellValue(DATE_FORMAT.format(post.getCreated_at()));
        }
    }
}
