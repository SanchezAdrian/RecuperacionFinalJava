package example.component;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

import com.lowagie.text.Cell;
import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Phrase;
import com.lowagie.text.Table;
import com.lowagie.text.pdf.PdfWriter;
import example.model.EquipoModel;

public class PDF {

	 public void PDFEquipo(List<EquipoModel> lista) {
	        Document doc = null;
	        
	        try {
	            doc = new Document();
	            PdfWriter.getInstance(doc, new FileOutputStream(".\\src\\main\\resources\\static\\pdf\\equipo.pdf"));
	            Table table = new Table(1);
	            table.setWidth(100);
	            Cell celda = new Cell("Equipos");
	            table.addCell(celda);
	            
	            table.addCell(new Phrase("Nombre equipo"));
	            table.addCell(new Phrase("Descripcion equipo"));
	            table.addCell(new Phrase("Fecha creación"));
	            table.endHeaders();
	            
	            for(EquipoModel equipoModel: lista) {
	                table.addCell(equipoModel.getNombre());
	                table.addCell(equipoModel.getDescripcion());
	                table.addCell(equipoModel.getCreatedat());
	            }
	            doc.open();
	            doc.add(table);
	            doc.close();
	        } catch (DocumentException | FileNotFoundException e) {
	            e.printStackTrace();
	        }
	    }
}
