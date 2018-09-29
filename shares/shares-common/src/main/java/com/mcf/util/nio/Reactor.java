package com.mcf.util.nio;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Reactor implements Runnable {

	private static ServerSocketChannel serverSocketChannel;
	private static Selector selector;

	public void run() {
		try {
			serverSocketChannel = ServerSocketChannel.open();
			selector = Selector.open();
			serverSocketChannel.configureBlocking(false);
			serverSocketChannel.bind(new InetSocketAddress(8899));
			SelectionKey selectionKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
			selectionKey.attach(new Acceptor(selector, selectionKey));
			System.out.println("系统服务启动。。。。");
			while (true) {
				System.out.println("---1");
				selector.select();
				Set<SelectionKey> keys = selector.selectedKeys();
				for (SelectionKey key : keys) {
					System.out.println("----key = " + key);
					// 分发事件
					dispatch(key);
					keys.remove(key);
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	// 分发事件
	private void dispatch(SelectionKey key) {
		Runnable acceptor = (Runnable) key.attachment();
		acceptor.run();
	}

	// 对应的接收连接事件处理器
	class Acceptor implements Runnable {
		Selector selector;
		SelectionKey selectionKey;

		public Acceptor(Selector selector, SelectionKey selectionKey) {
			this.selector = selector;
			this.selectionKey = selectionKey;
		}

		public void run() {
			System.out.println("连接进入。。。。");
			try {
				ServerSocketChannel serverSocketChannel = (ServerSocketChannel) selectionKey.channel();
				SocketChannel socketChannel = serverSocketChannel.accept();
				socketChannel.configureBlocking(false);
				SelectionKey key = socketChannel.register(selector, SelectionKey.OP_READ);
				key.attach(new ReadHandler(key, selector));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

	// 对应的读事件的处理器
	class ReadHandler implements Runnable {
		SelectionKey selectionKey;
		Selector selector;

		public ReadHandler(SelectionKey selectionKey, Selector selector) {
			this.selectionKey = selectionKey;
			this.selector = selector;
		}

		public void run() {
			System.out.println("读入数据。。。");
			SocketChannel socketChannel = (SocketChannel) selectionKey.channel();
			ByteBuffer byteBuffer = ByteBuffer.allocate(1024);
			try {
				socketChannel.read(byteBuffer);
				byteBuffer.flip();
				File file = new File("C:/Users/Administrator/Desktop/aa.txt");
				FileOutputStream fileOutputStream = new FileOutputStream(file);
				FileChannel fileChannel = fileOutputStream.getChannel();
				fileChannel.write(byteBuffer);
				fileOutputStream.close();
				System.out.println("完成数据。。。");
				// byteBuffer.flip();
				// socketChannel.write(byteBuffer);
				// selector.wakeup();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		Reactor reactor = new Reactor();
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		executorService.submit(reactor);
		
	}

}
