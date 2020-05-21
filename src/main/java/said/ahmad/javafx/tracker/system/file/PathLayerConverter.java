package said.ahmad.javafx.tracker.system.file;

import java.net.URISyntaxException;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;

public class PathLayerConverter implements Converter {

	@SuppressWarnings("rawtypes")
	@Override
	public boolean canConvert(Class type) {
		return PathLayer.class.isAssignableFrom(type);
	}

	@Override
	public void marshal(Object source, HierarchicalStreamWriter writer, MarshallingContext context) {
		PathLayer path = (PathLayer) source;
		writer.startNode("PathURI");
		writer.setValue(path.toURI().toString());
		writer.endNode();
	}

	@Override
	public Object unmarshal(HierarchicalStreamReader reader, UnmarshallingContext context) {
		reader.moveDown();
		PathLayer path = null;
		try {
			path = PathLayerHelper.parseURI(reader.getValue());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		reader.moveUp();
		return path;
	}

}
