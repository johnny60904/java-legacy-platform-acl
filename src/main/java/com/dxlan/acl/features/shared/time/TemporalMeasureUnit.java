package com.dxlan.acl.features.shared.time;

import com.dxlan.acl.features.shared.common.NameDisplayable;
import com.dxlan.acl.features.shared.lookup.Lookupable;

public interface TemporalMeasureUnit extends NameDisplayable, Lookupable {

    String name();

}
