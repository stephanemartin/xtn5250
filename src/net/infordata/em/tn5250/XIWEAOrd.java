/*
Copyright 2007 Infordata S.p.A.

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
*/

/*
    ***
    30/06/98 rel. _.___- Swing, JBuilder2 e VSS.
 */
 
 
package net.infordata.em.tn5250;


import java.io.IOException;
import java.io.InputStream;

import net.infordata.em.tnprot.XITelnet;


///////////////////////////////////////////////////////////////////////////////

/**
 * WEA - Write extended attribute
 * TODO
 * 
 * see: http://publibfp.boulder.ibm.com/cgi-bin/bookmgr/BOOKS/co2e2001/15.6.11?DT=19950629163252
 *
 * @version  
 * @author   Valentino Proietti - Infordata S.p.A.
 */
public class XIWEAOrd extends XI5250Ord {

  protected byte ivAttributeType;
  protected byte ivAttribute;

  /**
   * @exception    XI5250Exception    raised if order parameters are wrong.
   */
  @Override
  protected void readFrom5250Stream(InputStream inStream)
      throws IOException, XI5250Exception {
    // If not in enhanced mode ...
    if (true)
      throw new XI5250Exception("Not supported", XI5250Emulator.ERR_INVALID_ROW_COL_ADDR);
    {
      byte[] buf = new byte[2];
      if (inStream.read(buf) < buf.length)
        throw new XI5250Exception("EOF reached", XI5250Emulator.ERR_INVALID_EXT_ATTR_TYPE);
      ivAttributeType = buf[0];
      ivAttribute = buf[1];
      if (ivAttributeType != 0x01 && ivAttributeType != 0x03 && ivAttributeType != 0x05)
        throw new XI5250Exception("Invalid attr type: " + XITelnet.toHex(ivAttributeType), 
            XI5250Emulator.ERR_INVALID_EXT_ATTR_TYPE);
    }
  }


  @Override
  protected void execute() {
    //TODO
    throw new IllegalStateException("Not supported");
  }


  @Override
  public String toString() {
    return super.toString() + " [" + XITelnet.toHex(ivAttributeType) + "," + 
        XITelnet.toHex(ivAttribute) + "]";
  }
}