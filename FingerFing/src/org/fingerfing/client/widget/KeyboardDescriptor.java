package org.fingerfing.client.widget;

import java.util.List;

import org.fingerfing.client.core.NativeKey;

public interface KeyboardDescriptor {

	public static interface KeyDescriptor {
		
		public NativeKey getNativeKey();

		public String getWidth();

		public void setNativeKey(NativeKey nativeKey);

		public void setWidth(String width);
	}

	public static interface RowDescriptor {
		
		public List<KeyDescriptor> getRow();

		public void setRow(List<KeyDescriptor> row);
	}

	public List<RowDescriptor> getBlock();

	public void setBlock(List<RowDescriptor> block);

	
}
