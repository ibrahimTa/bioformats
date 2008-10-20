//
// ReaderWrapper.java
//

/*
OME Bio-Formats package for reading and converting biological file formats.
Copyright (C) 2005-@year@ UW-Madison LOCI and Glencoe Software, Inc.

This program is free software; you can redistribute it and/or modify
it under the terms of the GNU General Public License as published by
the Free Software Foundation; either version 3 of the License, or
(at your option) any later version.

This program is distributed in the hope that it will be useful,
but WITHOUT ANY WARRANTY; without even the implied warranty of
MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
GNU General Public License for more details.

You should have received a copy of the GNU General Public License
along with this program; if not, write to the Free Software
Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
*/

package loci.formats;

import java.awt.image.BufferedImage;
import java.io.*;
import java.util.Hashtable;
import loci.common.RandomAccessStream;
import loci.formats.meta.MetadataStore;

/**
 * Abstract superclass of reader logic that wraps other readers.
 * All methods are simply delegated to the wrapped reader.
 *
 * <dl><dt><b>Source code:</b></dt>
 * <dd><a href="https://skyking.microscopy.wisc.edu/trac/java/browser/trunk/components/bio-formats/src/loci/formats/ReaderWrapper.java">Trac</a>,
 * <a href="https://skyking.microscopy.wisc.edu/svn/java/trunk/components/bio-formats/src/loci/formats/ReaderWrapper.java">SVN</a></dd></dl>
 */
public abstract class ReaderWrapper implements IFormatReader {

  // -- Fields --

  /** FormatReader used to read the file. */
  protected IFormatReader reader;

  // -- Constructors --

  /** Constructs a reader wrapper around a new image reader. */
  public ReaderWrapper() { this(new ImageReader()); }

  /** Constructs a reader wrapper around the given reader. */
  public ReaderWrapper(IFormatReader r) {
    if (r == null) {
      throw new IllegalArgumentException("Format reader cannot be null");
    }
    reader = r;
  }

  // -- ReaderWrapper API methods --

  /** Gets the wrapped reader. */
  public IFormatReader getReader() { return reader; }

  // -- IFormatReader API methods --

  public boolean isThisType(byte[] block) {
    return reader.isThisType(block);
  }

  public boolean isThisType(RandomAccessStream stream) throws IOException{
    return reader.isThisType(stream);
  }

  public void setId(String id) throws FormatException, IOException {
    reader.setId(id);
  }

  public int getImageCount() {
    return reader.getImageCount();
  }

  public boolean isRGB() {
    return reader.isRGB();
  }

  public int getSizeX() {
    return reader.getSizeX();
  }

  public int getSizeY() {
    return reader.getSizeY();
  }

  public int getSizeZ() {
    return reader.getSizeZ();
  }

  public int getSizeC() {
    return reader.getSizeC();
  }

  public int getSizeT() {
    return reader.getSizeT();
  }

  public int getPixelType() {
    return reader.getPixelType();
  }

  public int getEffectiveSizeC() {
    return getImageCount() / (getSizeZ() * getSizeT());
  }

  public int getRGBChannelCount() {
    return getSizeC() / getEffectiveSizeC();
  }

  public boolean isIndexed() {
    return reader.isIndexed();
  }

  public boolean isFalseColor() {
    return reader.isFalseColor();
  }

  public byte[][] get8BitLookupTable() throws FormatException, IOException {
    return reader.get8BitLookupTable();
  }

  public short[][] get16BitLookupTable() throws FormatException, IOException {
    return reader.get16BitLookupTable();
  }

  public int[] getChannelDimLengths() {
    return reader.getChannelDimLengths();
  }

  public String[] getChannelDimTypes() {
    return reader.getChannelDimTypes();
  }

  public int getThumbSizeX() {
    return reader.getThumbSizeX();
  }

  public int getThumbSizeY() {
    return reader.getThumbSizeY();
  }

  public boolean isLittleEndian() {
    return reader.isLittleEndian();
  }

  public String getDimensionOrder() {
    return reader.getDimensionOrder();
  }

  public boolean isOrderCertain() {
    return reader.isOrderCertain();
  }

  public boolean isInterleaved() {
    return reader.isInterleaved();
  }

  public boolean isInterleaved(int subC) {
    return reader.isInterleaved(subC);
  }

  public BufferedImage openImage(int no) throws FormatException, IOException {
    return reader.openImage(no);
  }

  public BufferedImage openImage(int no, int x, int y, int w, int h)
    throws FormatException, IOException
  {
    return reader.openImage(no, x, y, w, h);
  }

  public byte[] openBytes(int no) throws FormatException, IOException {
    return reader.openBytes(no);
  }

  public byte[] openBytes(int no, int x, int y, int w, int h)
    throws FormatException, IOException
  {
    return reader.openBytes(no, x, y, w, h);
  }

  public byte[] openBytes(int no, byte[] buf)
    throws FormatException, IOException
  {
    return reader.openBytes(no, buf);
  }

  public byte[] openBytes(int no, byte[] buf, int x, int y, int w, int h)
    throws FormatException, IOException
  {
    return reader.openBytes(no, buf, x, y, w, h);
  }

  public BufferedImage openThumbImage(int no)
    throws FormatException, IOException
  {
    return reader.openThumbImage(no);
  }

  public byte[] openThumbBytes(int no) throws FormatException, IOException {
    return reader.openThumbBytes(no);
  }

  public void close(boolean fileOnly) throws IOException {
    reader.close(fileOnly);
  }

  public void close() throws IOException {
    reader.close();
  }

  public int getSeriesCount() {
    return reader.getSeriesCount();
  }

  public void setSeries(int no) {
    reader.setSeries(no);
  }

  public int getSeries() {
    return reader.getSeries();
  }

  public void setGroupFiles(boolean group) {
    reader.setGroupFiles(group);
  }

  public boolean isGroupFiles() {
    return reader.isGroupFiles();
  }

  public int fileGroupOption(String id) throws FormatException, IOException {
    return reader.fileGroupOption(id);
  }

  public boolean isMetadataComplete() {
    return reader.isMetadataComplete();
  }

  public void setNormalized(boolean normalize) {
    reader.setNormalized(normalize);
  }

  public boolean isNormalized() { return reader.isNormalized(); }

  public void setMetadataCollected(boolean collect) {
    reader.setMetadataCollected(collect);
  }

  public boolean isMetadataCollected() { return reader.isMetadataCollected(); }

  public void setOriginalMetadataPopulated(boolean populate) {
    reader.setOriginalMetadataPopulated(populate);
  }

  public boolean isOriginalMetadataPopulated() {
    return reader.isOriginalMetadataPopulated();
  }

  public String[] getUsedFiles() {
    return reader.getUsedFiles();
  }

  public String getCurrentFile() { return reader.getCurrentFile(); }

  public int getIndex(int z, int c, int t) {
    return reader.getIndex(z, c, t);
  }

  public int[] getZCTCoords(int index) {
    return reader.getZCTCoords(index);
  }

  public Object getMetadataValue(String field) {
    return reader.getMetadataValue(field);
  }

  public Hashtable getMetadata() {
    return reader.getMetadata();
  }

  public CoreMetadata[] getCoreMetadata() {
    return reader.getCoreMetadata();
  }

  public void setMetadataFiltered(boolean filter) {
    reader.setMetadataFiltered(filter);
  }

  public boolean isMetadataFiltered() { return reader.isMetadataFiltered(); }

  public void setMetadataStore(MetadataStore store) {
    reader.setMetadataStore(store);
  }

  public MetadataStore getMetadataStore() {
    return reader.getMetadataStore();
  }

  public Object getMetadataStoreRoot() {
    return reader.getMetadataStoreRoot();
  }

  public IFormatReader[] getUnderlyingReaders() {
    return new IFormatReader[] {reader};
  }

  // -- IFormatHandler API methods --

  public boolean isThisType(String name) {
    return reader.isThisType(name);
  }

  public boolean isThisType(String name, boolean open) {
    return reader.isThisType(name, open);
  }

  public String getFormat() {
    return reader.getFormat();
  }

  public String[] getSuffixes() {
    return reader.getSuffixes();
  }

  // -- StatusReporter API methods --

  public void addStatusListener(StatusListener l) {
    reader.addStatusListener(l);
  }

  public void removeStatusListener(StatusListener l) {
    reader.removeStatusListener(l);
  }

  public StatusListener[] getStatusListeners() {
    return reader.getStatusListeners();
  }

}
