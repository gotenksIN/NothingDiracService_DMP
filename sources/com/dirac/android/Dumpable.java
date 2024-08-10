package com.dirac.android;

import java.io.PrintWriter;

/* loaded from: classes4.dex */
public interface Dumpable {
    void dump(Writer writer);

    /* loaded from: classes4.dex */
    public static final class Writer {
        private final PrintWriter printWriter;

        public Writer(PrintWriter printWriter) {
            this.printWriter = printWriter;
        }

        public Writer a(double d) {
            this.printWriter.print(d);
            return this;
        }

        public Writer a(char c) {
            this.printWriter.print(c);
            return this;
        }

        public Writer a(boolean b) {
            this.printWriter.print(b);
            return this;
        }

        public Writer a(long l) {
            this.printWriter.print(l);
            return this;
        }

        public Writer a(float f) {
            this.printWriter.print(f);
            return this;
        }

        public Writer a(char[] chars) {
            this.printWriter.print(chars);
            return this;
        }

        public Writer a(int i) {
            this.printWriter.print(i);
            return this;
        }

        public Writer a(String str) {
            this.printWriter.print(str);
            return this;
        }

        public Writer a(Object obj) {
            this.printWriter.print(obj.toString());
            return this;
        }

        public Writer pad(int num) {
            for (int i = 0; i < num; i++) {
                this.printWriter.append(' ');
            }
            return this;
        }

        public Writer nl() {
            this.printWriter.println();
            return this;
        }
    }
}
