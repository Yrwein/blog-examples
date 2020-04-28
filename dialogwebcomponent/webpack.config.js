
const path = require('path');

module.exports = {
  entry: './src/index.js',
  output: {
    filename: 'main.js',
    path: path.resolve(__dirname, 'dist'),
  },
  module: {
    rules: [
      {
        test: /\.css$/i,
        loader: 'file-loader',
        options: {
          outputPath: 'css',
        },
      },
      {
        test: /\.html$/i,
        loader: 'raw-loader',
      },
    ],
  },
};
