FROM eclipse-temurin:17-jdk

# 環境変数
ENV SCALA_VERSION=2.13.12 \
    SBT_VERSION=1.9.9

# 必要ツール
RUN apt-get update && apt-get install -y \
    curl \
    git \
    ca-certificates \
    && rm -rf /var/lib/apt/lists/*

# sbt インストール
RUN curl -fsL https://github.com/sbt/sbt/releases/download/v${SBT_VERSION}/sbt-${SBT_VERSION}.tgz \
    | tar -xz -C /opt \
    && ln -s /opt/sbt/bin/sbt /usr/local/bin/sbt

# 作業ディレクトリ
WORKDIR /workspace

# sbt 初回起動を軽くしておく（キャッシュ作成）
RUN sbt sbtVersion

CMD ["bash"]
