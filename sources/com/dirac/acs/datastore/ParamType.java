package com.dirac.acs.datastore;

import com.google.protobuf.Descriptors;
import com.google.protobuf.Internal;
import com.google.protobuf.ProtocolMessageEnum;

/* loaded from: classes4.dex */
public enum ParamType implements ProtocolMessageEnum {
    PARAM_TYPE_LINEAR(0),
    PARAM_TYPE_LOGARITHMIC(1),
    PARAM_TYPE_TOGGLE(2),
    PARAM_TYPE_ENUMERATION(3),
    UNRECOGNIZED(-1);

    public static final int PARAM_TYPE_ENUMERATION_VALUE = 3;
    public static final int PARAM_TYPE_LINEAR_VALUE = 0;
    public static final int PARAM_TYPE_LOGARITHMIC_VALUE = 1;
    public static final int PARAM_TYPE_TOGGLE_VALUE = 2;
    private final int value;
    private static final Internal.EnumLiteMap<ParamType> internalValueMap = new Internal.EnumLiteMap<ParamType>() { // from class: com.dirac.acs.datastore.ParamType.1
        /* renamed from: findValueByNumber, reason: merged with bridge method [inline-methods] */
        public ParamType m455findValueByNumber(int number) {
            return ParamType.forNumber(number);
        }
    };
    private static final ParamType[] VALUES = values();

    public final int getNumber() {
        if (this == UNRECOGNIZED) {
            throw new IllegalArgumentException("Can't get the number of an unknown enum value.");
        }
        return this.value;
    }

    @Deprecated
    public static ParamType valueOf(int value) {
        return forNumber(value);
    }

    public static ParamType forNumber(int value) {
        switch (value) {
            case 0:
                return PARAM_TYPE_LINEAR;
            case 1:
                return PARAM_TYPE_LOGARITHMIC;
            case 2:
                return PARAM_TYPE_TOGGLE;
            case 3:
                return PARAM_TYPE_ENUMERATION;
            default:
                return null;
        }
    }

    public static Internal.EnumLiteMap<ParamType> internalGetValueMap() {
        return internalValueMap;
    }

    public final Descriptors.EnumValueDescriptor getValueDescriptor() {
        return (Descriptors.EnumValueDescriptor) getDescriptor().getValues().get(ordinal());
    }

    public final Descriptors.EnumDescriptor getDescriptorForType() {
        return getDescriptor();
    }

    public static final Descriptors.EnumDescriptor getDescriptor() {
        return (Descriptors.EnumDescriptor) ParameterMessages.getDescriptor().getEnumTypes().get(0);
    }

    public static ParamType valueOf(Descriptors.EnumValueDescriptor desc) {
        if (desc.getType() != getDescriptor()) {
            throw new IllegalArgumentException("EnumValueDescriptor is not for this type.");
        }
        if (desc.getIndex() == -1) {
            return UNRECOGNIZED;
        }
        return VALUES[desc.getIndex()];
    }

    ParamType(int value) {
        this.value = value;
    }
}
