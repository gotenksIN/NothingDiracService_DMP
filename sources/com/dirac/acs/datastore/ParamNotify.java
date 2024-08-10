package com.dirac.acs.datastore;

import com.dirac.acs.datastore.Ids;
import com.google.protobuf.AbstractParser;
import com.google.protobuf.ByteString;
import com.google.protobuf.CodedInputStream;
import com.google.protobuf.CodedOutputStream;
import com.google.protobuf.Descriptors;
import com.google.protobuf.ExtensionRegistryLite;
import com.google.protobuf.GeneratedMessageV3;
import com.google.protobuf.InvalidProtocolBufferException;
import com.google.protobuf.Message;
import com.google.protobuf.Parser;
import com.google.protobuf.SingleFieldBuilderV3;
import com.google.protobuf.UnknownFieldSet;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;

/* loaded from: classes4.dex */
public final class ParamNotify extends GeneratedMessageV3 implements ParamNotifyOrBuilder {
    public static final int CHANGE_ID_FIELD_NUMBER = 1;
    public static final int IDS_FIELD_NUMBER = 2;
    private static final long serialVersionUID = 0;
    private volatile Object changeId_;
    private Ids ids_;
    private byte memoizedIsInitialized;
    private static final ParamNotify DEFAULT_INSTANCE = new ParamNotify();
    private static final Parser<ParamNotify> PARSER = new AbstractParser<ParamNotify>() { // from class: com.dirac.acs.datastore.ParamNotify.1
        /* renamed from: parsePartialFrom, reason: merged with bridge method [inline-methods] */
        public ParamNotify m416parsePartialFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
            return new ParamNotify(input, extensionRegistry);
        }
    };

    private ParamNotify(GeneratedMessageV3.Builder<?> builder) {
        super(builder);
        this.memoizedIsInitialized = (byte) -1;
    }

    private ParamNotify() {
        this.memoizedIsInitialized = (byte) -1;
        this.changeId_ = "";
    }

    protected Object newInstance(GeneratedMessageV3.UnusedPrivateParameter unused) {
        return new ParamNotify();
    }

    public final UnknownFieldSet getUnknownFields() {
        return this.unknownFields;
    }

    /* JADX WARN: Failed to find 'out' block for switch in B:7:0x0010. Please report as an issue. */
    private ParamNotify(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        this();
        if (extensionRegistry == null) {
            throw new NullPointerException();
        }
        UnknownFieldSet.Builder unknownFields = UnknownFieldSet.newBuilder();
        boolean done = false;
        while (!done) {
            try {
                try {
                    int tag = input.readTag();
                    switch (tag) {
                        case 0:
                            done = true;
                        case 10:
                            String s = input.readStringRequireUtf8();
                            this.changeId_ = s;
                        case 18:
                            Ids ids = this.ids_;
                            Ids.Builder subBuilder = ids != null ? ids.m251toBuilder() : null;
                            Ids ids2 = (Ids) input.readMessage(Ids.parser(), extensionRegistry);
                            this.ids_ = ids2;
                            if (subBuilder != null) {
                                subBuilder.mergeFrom(ids2);
                                this.ids_ = subBuilder.m258buildPartial();
                            }
                        default:
                            if (!parseUnknownField(input, unknownFields, extensionRegistry, tag)) {
                                done = true;
                            }
                    }
                } catch (IOException e) {
                    throw new InvalidProtocolBufferException(e).setUnfinishedMessage(this);
                } catch (InvalidProtocolBufferException e2) {
                    throw e2.setUnfinishedMessage(this);
                }
            } finally {
                this.unknownFields = unknownFields.build();
                makeExtensionsImmutable();
            }
        }
    }

    public static final Descriptors.Descriptor getDescriptor() {
        return ParameterMessages.internal_static_com_dirac_acs_datastore_ParamNotify_descriptor;
    }

    protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
        return ParameterMessages.internal_static_com_dirac_acs_datastore_ParamNotify_fieldAccessorTable.ensureFieldAccessorsInitialized(ParamNotify.class, Builder.class);
    }

    @Override // com.dirac.acs.datastore.ParamNotifyOrBuilder
    public String getChangeId() {
        Object ref = this.changeId_;
        if (ref instanceof String) {
            return (String) ref;
        }
        ByteString bs = (ByteString) ref;
        String s = bs.toStringUtf8();
        this.changeId_ = s;
        return s;
    }

    @Override // com.dirac.acs.datastore.ParamNotifyOrBuilder
    public ByteString getChangeIdBytes() {
        Object ref = this.changeId_;
        if (ref instanceof String) {
            ByteString b = ByteString.copyFromUtf8((String) ref);
            this.changeId_ = b;
            return b;
        }
        return (ByteString) ref;
    }

    @Override // com.dirac.acs.datastore.ParamNotifyOrBuilder
    public boolean hasIds() {
        return this.ids_ != null;
    }

    @Override // com.dirac.acs.datastore.ParamNotifyOrBuilder
    public Ids getIds() {
        Ids ids = this.ids_;
        return ids == null ? Ids.getDefaultInstance() : ids;
    }

    @Override // com.dirac.acs.datastore.ParamNotifyOrBuilder
    public IdsOrBuilder getIdsOrBuilder() {
        return getIds();
    }

    public final boolean isInitialized() {
        byte isInitialized = this.memoizedIsInitialized;
        if (isInitialized == 1) {
            return true;
        }
        if (isInitialized == 0) {
            return false;
        }
        this.memoizedIsInitialized = (byte) 1;
        return true;
    }

    public void writeTo(CodedOutputStream output) throws IOException {
        if (!getChangeIdBytes().isEmpty()) {
            GeneratedMessageV3.writeString(output, 1, this.changeId_);
        }
        if (this.ids_ != null) {
            output.writeMessage(2, getIds());
        }
        this.unknownFields.writeTo(output);
    }

    public int getSerializedSize() {
        int size = this.memoizedSize;
        if (size != -1) {
            return size;
        }
        int size2 = getChangeIdBytes().isEmpty() ? 0 : 0 + GeneratedMessageV3.computeStringSize(1, this.changeId_);
        if (this.ids_ != null) {
            size2 += CodedOutputStream.computeMessageSize(2, getIds());
        }
        int size3 = size2 + this.unknownFields.getSerializedSize();
        this.memoizedSize = size3;
        return size3;
    }

    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof ParamNotify)) {
            return super.equals(obj);
        }
        ParamNotify other = (ParamNotify) obj;
        if (getChangeId().equals(other.getChangeId()) && hasIds() == other.hasIds()) {
            return (!hasIds() || getIds().equals(other.getIds())) && this.unknownFields.equals(other.unknownFields);
        }
        return false;
    }

    public int hashCode() {
        if (this.memoizedHashCode != 0) {
            return this.memoizedHashCode;
        }
        int hash = (((((41 * 19) + getDescriptor().hashCode()) * 37) + 1) * 53) + getChangeId().hashCode();
        if (hasIds()) {
            hash = (((hash * 37) + 2) * 53) + getIds().hashCode();
        }
        int hash2 = (hash * 29) + this.unknownFields.hashCode();
        this.memoizedHashCode = hash2;
        return hash2;
    }

    public static ParamNotify parseFrom(ByteBuffer data) throws InvalidProtocolBufferException {
        return (ParamNotify) PARSER.parseFrom(data);
    }

    public static ParamNotify parseFrom(ByteBuffer data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ParamNotify) PARSER.parseFrom(data, extensionRegistry);
    }

    public static ParamNotify parseFrom(ByteString data) throws InvalidProtocolBufferException {
        return (ParamNotify) PARSER.parseFrom(data);
    }

    public static ParamNotify parseFrom(ByteString data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ParamNotify) PARSER.parseFrom(data, extensionRegistry);
    }

    public static ParamNotify parseFrom(byte[] data) throws InvalidProtocolBufferException {
        return (ParamNotify) PARSER.parseFrom(data);
    }

    public static ParamNotify parseFrom(byte[] data, ExtensionRegistryLite extensionRegistry) throws InvalidProtocolBufferException {
        return (ParamNotify) PARSER.parseFrom(data, extensionRegistry);
    }

    public static ParamNotify parseFrom(InputStream input) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static ParamNotify parseFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }

    public static ParamNotify parseDelimitedFrom(InputStream input) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input);
    }

    public static ParamNotify parseDelimitedFrom(InputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseDelimitedWithIOException(PARSER, input, extensionRegistry);
    }

    public static ParamNotify parseFrom(CodedInputStream input) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input);
    }

    public static ParamNotify parseFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
        return GeneratedMessageV3.parseWithIOException(PARSER, input, extensionRegistry);
    }

    /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m413newBuilderForType() {
        return newBuilder();
    }

    public static Builder newBuilder() {
        return DEFAULT_INSTANCE.m415toBuilder();
    }

    public static Builder newBuilder(ParamNotify prototype) {
        return DEFAULT_INSTANCE.m415toBuilder().mergeFrom(prototype);
    }

    /* renamed from: toBuilder, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public Builder m415toBuilder() {
        return this == DEFAULT_INSTANCE ? new Builder() : new Builder().mergeFrom(this);
    }

    /* JADX INFO: Access modifiers changed from: protected */
    /* renamed from: newBuilderForType, reason: merged with bridge method [inline-methods] */
    public Builder m412newBuilderForType(GeneratedMessageV3.BuilderParent parent) {
        Builder builder = new Builder(parent);
        return builder;
    }

    /* loaded from: classes4.dex */
    public static final class Builder extends GeneratedMessageV3.Builder<Builder> implements ParamNotifyOrBuilder {
        private Object changeId_;
        private SingleFieldBuilderV3<Ids, Ids.Builder, IdsOrBuilder> idsBuilder_;
        private Ids ids_;

        public static final Descriptors.Descriptor getDescriptor() {
            return ParameterMessages.internal_static_com_dirac_acs_datastore_ParamNotify_descriptor;
        }

        protected GeneratedMessageV3.FieldAccessorTable internalGetFieldAccessorTable() {
            return ParameterMessages.internal_static_com_dirac_acs_datastore_ParamNotify_fieldAccessorTable.ensureFieldAccessorsInitialized(ParamNotify.class, Builder.class);
        }

        private Builder() {
            this.changeId_ = "";
            maybeForceBuilderInitialization();
        }

        private Builder(GeneratedMessageV3.BuilderParent parent) {
            super(parent);
            this.changeId_ = "";
            maybeForceBuilderInitialization();
        }

        private void maybeForceBuilderInitialization() {
            boolean unused = ParamNotify.alwaysUseFieldBuilders;
        }

        /* renamed from: clear, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m426clear() {
            super.clear();
            this.changeId_ = "";
            if (this.idsBuilder_ == null) {
                this.ids_ = null;
            } else {
                this.ids_ = null;
                this.idsBuilder_ = null;
            }
            return this;
        }

        public Descriptors.Descriptor getDescriptorForType() {
            return ParameterMessages.internal_static_com_dirac_acs_datastore_ParamNotify_descriptor;
        }

        /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ParamNotify m439getDefaultInstanceForType() {
            return ParamNotify.getDefaultInstance();
        }

        /* renamed from: build, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ParamNotify m420build() {
            ParamNotify result = m422buildPartial();
            if (!result.isInitialized()) {
                throw newUninitializedMessageException(result);
            }
            return result;
        }

        /* renamed from: buildPartial, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public ParamNotify m422buildPartial() {
            ParamNotify result = new ParamNotify(this);
            result.changeId_ = this.changeId_;
            SingleFieldBuilderV3<Ids, Ids.Builder, IdsOrBuilder> singleFieldBuilderV3 = this.idsBuilder_;
            if (singleFieldBuilderV3 == null) {
                result.ids_ = this.ids_;
            } else {
                result.ids_ = singleFieldBuilderV3.build();
            }
            onBuilt();
            return result;
        }

        /* renamed from: clone, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m437clone() {
            return (Builder) super.clone();
        }

        /* renamed from: setField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m450setField(Descriptors.FieldDescriptor field, Object value) {
            return (Builder) super.setField(field, value);
        }

        /* renamed from: clearField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m428clearField(Descriptors.FieldDescriptor field) {
            return (Builder) super.clearField(field);
        }

        /* renamed from: clearOneof, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m431clearOneof(Descriptors.OneofDescriptor oneof) {
            return (Builder) super.clearOneof(oneof);
        }

        /* renamed from: setRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m452setRepeatedField(Descriptors.FieldDescriptor field, int index, Object value) {
            return (Builder) super.setRepeatedField(field, index, value);
        }

        /* renamed from: addRepeatedField, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m418addRepeatedField(Descriptors.FieldDescriptor field, Object value) {
            return (Builder) super.addRepeatedField(field, value);
        }

        /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m444mergeFrom(Message other) {
            if (other instanceof ParamNotify) {
                return mergeFrom((ParamNotify) other);
            }
            super.mergeFrom(other);
            return this;
        }

        public Builder mergeFrom(ParamNotify other) {
            if (other == ParamNotify.getDefaultInstance()) {
                return this;
            }
            if (!other.getChangeId().isEmpty()) {
                this.changeId_ = other.changeId_;
                onChanged();
            }
            if (other.hasIds()) {
                mergeIds(other.getIds());
            }
            m448mergeUnknownFields(other.unknownFields);
            onChanged();
            return this;
        }

        public final boolean isInitialized() {
            return true;
        }

        /* renamed from: mergeFrom, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public Builder m445mergeFrom(CodedInputStream input, ExtensionRegistryLite extensionRegistry) throws IOException {
            ParamNotify parsedMessage = null;
            try {
                try {
                    parsedMessage = (ParamNotify) ParamNotify.PARSER.parsePartialFrom(input, extensionRegistry);
                    return this;
                } catch (InvalidProtocolBufferException e) {
                    e.getUnfinishedMessage();
                    throw e.unwrapIOException();
                }
            } finally {
                if (parsedMessage != null) {
                    mergeFrom(parsedMessage);
                }
            }
        }

        @Override // com.dirac.acs.datastore.ParamNotifyOrBuilder
        public String getChangeId() {
            Object ref = this.changeId_;
            if (!(ref instanceof String)) {
                ByteString bs = (ByteString) ref;
                String s = bs.toStringUtf8();
                this.changeId_ = s;
                return s;
            }
            return (String) ref;
        }

        @Override // com.dirac.acs.datastore.ParamNotifyOrBuilder
        public ByteString getChangeIdBytes() {
            Object ref = this.changeId_;
            if (ref instanceof String) {
                ByteString b = ByteString.copyFromUtf8((String) ref);
                this.changeId_ = b;
                return b;
            }
            return (ByteString) ref;
        }

        public Builder setChangeId(String value) {
            if (value == null) {
                throw new NullPointerException();
            }
            this.changeId_ = value;
            onChanged();
            return this;
        }

        public Builder clearChangeId() {
            this.changeId_ = ParamNotify.getDefaultInstance().getChangeId();
            onChanged();
            return this;
        }

        public Builder setChangeIdBytes(ByteString value) {
            if (value != null) {
                ParamNotify.checkByteStringIsUtf8(value);
                this.changeId_ = value;
                onChanged();
                return this;
            }
            throw new NullPointerException();
        }

        @Override // com.dirac.acs.datastore.ParamNotifyOrBuilder
        public boolean hasIds() {
            return (this.idsBuilder_ == null && this.ids_ == null) ? false : true;
        }

        @Override // com.dirac.acs.datastore.ParamNotifyOrBuilder
        public Ids getIds() {
            SingleFieldBuilderV3<Ids, Ids.Builder, IdsOrBuilder> singleFieldBuilderV3 = this.idsBuilder_;
            if (singleFieldBuilderV3 == null) {
                Ids ids = this.ids_;
                return ids == null ? Ids.getDefaultInstance() : ids;
            }
            return singleFieldBuilderV3.getMessage();
        }

        public Builder setIds(Ids value) {
            SingleFieldBuilderV3<Ids, Ids.Builder, IdsOrBuilder> singleFieldBuilderV3 = this.idsBuilder_;
            if (singleFieldBuilderV3 == null) {
                if (value == null) {
                    throw new NullPointerException();
                }
                this.ids_ = value;
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(value);
            }
            return this;
        }

        public Builder setIds(Ids.Builder builderForValue) {
            SingleFieldBuilderV3<Ids, Ids.Builder, IdsOrBuilder> singleFieldBuilderV3 = this.idsBuilder_;
            if (singleFieldBuilderV3 == null) {
                this.ids_ = builderForValue.m256build();
                onChanged();
            } else {
                singleFieldBuilderV3.setMessage(builderForValue.m256build());
            }
            return this;
        }

        public Builder mergeIds(Ids value) {
            SingleFieldBuilderV3<Ids, Ids.Builder, IdsOrBuilder> singleFieldBuilderV3 = this.idsBuilder_;
            if (singleFieldBuilderV3 == null) {
                Ids ids = this.ids_;
                if (ids != null) {
                    this.ids_ = Ids.newBuilder(ids).mergeFrom(value).m258buildPartial();
                } else {
                    this.ids_ = value;
                }
                onChanged();
            } else {
                singleFieldBuilderV3.mergeFrom(value);
            }
            return this;
        }

        public Builder clearIds() {
            if (this.idsBuilder_ == null) {
                this.ids_ = null;
                onChanged();
            } else {
                this.ids_ = null;
                this.idsBuilder_ = null;
            }
            return this;
        }

        public Ids.Builder getIdsBuilder() {
            onChanged();
            return getIdsFieldBuilder().getBuilder();
        }

        @Override // com.dirac.acs.datastore.ParamNotifyOrBuilder
        public IdsOrBuilder getIdsOrBuilder() {
            SingleFieldBuilderV3<Ids, Ids.Builder, IdsOrBuilder> singleFieldBuilderV3 = this.idsBuilder_;
            if (singleFieldBuilderV3 != null) {
                return (IdsOrBuilder) singleFieldBuilderV3.getMessageOrBuilder();
            }
            Ids ids = this.ids_;
            if (ids != null) {
                return ids;
            }
            return Ids.getDefaultInstance();
        }

        private SingleFieldBuilderV3<Ids, Ids.Builder, IdsOrBuilder> getIdsFieldBuilder() {
            if (this.idsBuilder_ == null) {
                this.idsBuilder_ = new SingleFieldBuilderV3<>(getIds(), getParentForChildren(), isClean());
                this.ids_ = null;
            }
            return this.idsBuilder_;
        }

        /* renamed from: setUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m454setUnknownFields(UnknownFieldSet unknownFields) {
            return (Builder) super.setUnknownFields(unknownFields);
        }

        /* renamed from: mergeUnknownFields, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
        public final Builder m448mergeUnknownFields(UnknownFieldSet unknownFields) {
            return (Builder) super.mergeUnknownFields(unknownFields);
        }
    }

    public static ParamNotify getDefaultInstance() {
        return DEFAULT_INSTANCE;
    }

    public static Parser<ParamNotify> parser() {
        return PARSER;
    }

    public Parser<ParamNotify> getParserForType() {
        return PARSER;
    }

    /* renamed from: getDefaultInstanceForType, reason: merged with bridge method [inline-methods] and merged with bridge method [inline-methods] */
    public ParamNotify m410getDefaultInstanceForType() {
        return DEFAULT_INSTANCE;
    }
}
