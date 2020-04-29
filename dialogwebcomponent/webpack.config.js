
const path = require('path');

module.exports = {
  entry: [
    './src/index.js',
    './src/index.html',
  ],
  output: {
    filename: 'main.js',
    path: path.resolve(__dirname, 'dist'),
  },
  module: {
    rules: [
      {
        test: /\.css$/i,
        use: [
          'file-loader?outputPath=css',
          'extract-loader',
          'css-loader',
        ],
      },
      {
        test: require.resolve('./src/index.html'),
        use: [
          'file-loader?name=index.html',
        ],
      },
      {
        test: /\.html$/i,
        exclude: [
          require.resolve('./src/index.html'),
        ],
        loader: 'html-loader',
      },
    ],
  },
};
