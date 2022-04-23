
installstan:
	wget https://github.com/stan-dev/cmdstan/releases/download/v2.29.2/cmdstan-2.29.2-linux-arm64.tar.gz -O bin/cmdstan-2.29.2.tar.gz
	tar -xzf bin/cmdstan2.29.2.tar.gz -C bin
	make -C bin/cmdstan-2.29.2 build
	rm bin/cmdstan-2.29.2.tar.gz
